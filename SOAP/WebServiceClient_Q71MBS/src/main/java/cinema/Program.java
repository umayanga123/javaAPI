package cinema;

import java.util.Arrays;
import java.util.List;

import jakarta.xml.ws.BindingProvider;
import seatreservation.CinemaService;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaInitCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.Seat;

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
		List<String> validTasks = Arrays.asList("Init","Lock", "Reserve", "Buy");
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
		
		try {
			// Perform the task based on the user input
			switch (task) {
			case "Init":
				cinemaService.init(Integer.valueOf(row), Integer.valueOf(column));		
				System.out.println("Seat Initalized "+row +":" +column);
				break;
			case "Lock":
				String lockId = cinemaService.lock(seat, 1);
				System.out.println("Seat locked successfully. Lock ID: " + lockId);
				break;
			case "Reserve":
				String reserveLockId = cinemaService.lock(seat, 1);
				cinemaService.reserve(reserveLockId);
				System.out.println("Seat reserved successfully.");
				break;
			case "Buy":
				String buyLockId = cinemaService.lock(seat, 1);
				cinemaService.buy(buyLockId);
				System.out.println("Seat bought successfully.");
				break;

			}
		} catch (ICinemaReserveCinemaException | ICinemaBuyCinemaException | ICinemaLockCinemaException | NumberFormatException | ICinemaInitCinemaException e) {
			System.err.println("Error occurred: " + e.getMessage());
		}
	}
}
