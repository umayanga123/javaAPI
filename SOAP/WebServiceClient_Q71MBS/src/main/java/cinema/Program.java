package cinema;

import java.util.Arrays;
import java.util.List;

import jakarta.xml.ws.BindingProvider;
import seatreservation.CinemaService;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaGetSeatStatusCinemaException;
import seatreservation.ICinemaInitCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.Seat;
import seatreservation.SeatStatus;

public class Program {

	public static void main(String args[]) {
		// Check if all required arguments are provided
		if (args.length != 4) {
			System.err.println("Usage: java cinema.Program [url] [row] [column] [task]");
			System.exit(1);
		}

		// Extract arguments
		String url = args[0];
		String row = args[1];
		String column = args[2];
		String task = args[3];

		// Validate task
		List<String> validTasks = Arrays.asList("Init", "Lock", "Reserve", "Buy");
		if (!validTasks.contains(task)) {
			System.err.println("Invalid task. Valid tasks are: Lock, Reserve, Buy");
			System.exit(1);
		}

		// Create a CinemaService client
		ICinema cinemaService = new CinemaService().getICinemaHttpSoap11Port();

		// Set the endpoint URL
		BindingProvider bindingProvider = (BindingProvider) cinemaService;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		Seat seat = new Seat();
		seat.setRow(row);
		seat.setColumn(column);
		String lockId = null;

		try {
			// Perform the task based on the user input
			cinemaService.init(Integer.valueOf(10), Integer.valueOf(20));
			System.out.println("Seat Initalized " + 10 + ":" + 20);

			switch (task) {
			case "Lock":
				lockId = cinemaService.lock(seat, 1);
				System.out.println("Seat locked successfully. Lock ID:" + lockId + " Seat Status :"
						+ cinemaService.getSeatStatus(seat));
				break;
			case "Reserve":
				SeatStatus status = cinemaService.getSeatStatus(seat);
				if (status == SeatStatus.FREE) {
					lockId = cinemaService.lock(seat, 1);
					System.out.println("Seat locked successfully. Lock ID: " + lockId);
				}
				cinemaService.reserve(lockId);
				System.out.println("Seat reserved successfully. Seat Status :" + cinemaService.getSeatStatus(seat));
				break;
			case "Buy":
				SeatStatus status1 = cinemaService.getSeatStatus(seat);
				if (status1 == SeatStatus.FREE) {
					lockId = cinemaService.lock(seat, 1);
					System.out.println("Seat locked successfully. Lock ID: " + lockId);
				}
				cinemaService.buy(lockId);
				System.out.println("Seat bought successfully. Seat Status :" + cinemaService.getSeatStatus(seat));
				break;

			}
		} catch (ICinemaReserveCinemaException | ICinemaBuyCinemaException | ICinemaLockCinemaException
				| NumberFormatException | ICinemaInitCinemaException | ICinemaGetSeatStatusCinemaException e) {
			System.err.println("Error occurred: " + e.getMessage());
		}
	}
}
