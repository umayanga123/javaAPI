package webService_Q71MBS;

import java.util.HashMap;
import java.util.Map;
import jakarta.jws.WebService;
import seatreservation.ArrayOfSeat;
import seatreservation.CinemaException;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaGetAllSeatsCinemaException;
import seatreservation.ICinemaGetSeatStatusCinemaException;
import seatreservation.ICinemaInitCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.ICinemaUnlockCinemaException;
import seatreservation.Seat;
import seatreservation.SeatStatus;

@WebService(name = "CinemaService", portName = "ICinema_HttpSoap11_Port", targetNamespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", endpointInterface = "seatreservation.ICinema", wsdlLocation = "WEB-INF/wsdl/SeatReservation.wsdl")
public class Cinema implements ICinema {

	private ArrayOfSeat allSeats;
	private Map<String, SeatInfo> seatStatusMap; // Map to store seat information
	private static int LockCount = 0;
	private static int MAX_ROWS; // Example maximum number of rows
	private static int MAX_COLUMNS; // Example maximum number of columns

	@Override
	public void init(int rows, int columns) throws ICinemaInitCinemaException {
		if (rows <= 0 || columns <= 0) {
			throw new ICinemaInitCinemaException("Invalid rows or columns provided.", new CinemaException());
		}
		MAX_ROWS = rows;
		MAX_COLUMNS = columns;
		LockCount = 0;
		allSeats = new ArrayOfSeat();
		seatStatusMap = new HashMap<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Seat seat = new Seat();
				seat.setRow(Integer.toString(i));
				seat.setColumn(Integer.toString(j));
				allSeats.getSeat().add(seat);
				String seatKey = generateSeatKey(i, j);
				seatStatusMap.put(seatKey, new SeatInfo(seat)); // Initialize with default status
			}
		}
	}

	// Utility method to generate a unique key for a seat based on row and column
	private String generateSeatKey(int row, int column) {
		return row + "-" + column;
	}

	@Override
	public ArrayOfSeat getAllSeats() throws ICinemaGetAllSeatsCinemaException {
		if (allSeats == null) {
			throw new ICinemaGetAllSeatsCinemaException("Cinema has not been initialized.", new CinemaException());
		}
		ArrayOfSeat allSeatsResponse = new ArrayOfSeat();

		// Loop through each row and column to create Seat objects and add them to the
		// response
		for (char row = 'A'; row <= 'J'; row++) {
			for (int column = 1; column <= 20; column++) {
				Seat seat = new Seat();
				seat.setRow(Character.toString(row));
				seat.setColumn(Integer.toString(column));
				allSeatsResponse.getSeat().add(seat);
			}
		}

		return allSeatsResponse;
	}

	@Override
	public SeatStatus getSeatStatus(Seat seat) throws ICinemaGetSeatStatusCinemaException {
		if (seat == null || seat.getRow() == null || seat.getColumn() == null) {
			throw new ICinemaGetSeatStatusCinemaException("Invalid seat provided.", new CinemaException());
		}

		int row;
		int column;

		try {
			// Convert the row letter to an integer
			row = seat.getRow().charAt(0) - 'A'; // Assuming 'A' is the starting letter
			column = Integer.parseInt(seat.getColumn()) - 1; // Adjust column to start from 0
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			throw new ICinemaGetSeatStatusCinemaException("Invalid seat format: " + e.getMessage(),
					new CinemaException());
		}

		// Check if the seat position is valid
		if (row < 0 || row >= MAX_ROWS || column < 0 || column >= MAX_COLUMNS) {
			throw new ICinemaGetSeatStatusCinemaException("Bad seat number", new CinemaException());
		}

		String seatKey = generateSeatKey(row, column);
		SeatInfo seatInfo = seatStatusMap.get(seatKey);

		if (seatInfo == null) {
			throw new ICinemaGetSeatStatusCinemaException("Invalid seat position.", new CinemaException());
		}

		return seatInfo.getStatus();

	}

	@Override
	public String lock(Seat seat, int count) throws ICinemaLockCinemaException {
		if (seat == null || seat.getRow() == null || seat.getColumn() == null) {
			throw new ICinemaLockCinemaException("Invalid seat provided.", new CinemaException());
		}

		int row;
		int column;

		try {
			// Convert the row letter to an integer
			row = seat.getRow().charAt(0) - 'A'; // Assuming 'A' is the starting letter
			column = Integer.parseInt(seat.getColumn()) - 1; // Adjust column to start from 0
		} catch (NumberFormatException e) {
			throw new ICinemaLockCinemaException("Invalid seat format.", new CinemaException());
		}

		// Check if the seat position is valid
		if (row < 0 || row > MAX_ROWS || column < 0 || column >= MAX_COLUMNS) {
			throw new ICinemaLockCinemaException("Invalid seat position.", new CinemaException());
		}

		// Check if the requested count exceeds the available seats in the row
		if ((column + count) >= MAX_COLUMNS) {
			throw new ICinemaLockCinemaException("Not enough seats", new CinemaException());
		}

		// Check if the requested seats are available
		for (int i = column; i < column + count; i++) {
			String currentSeatKey = generateSeatKey(row, i);
			SeatInfo seatInfo = seatStatusMap.get(currentSeatKey);
			if (seatInfo == null || seatInfo.getStatus() == SeatStatus.LOCKED) {
				throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());
			}
		}

		// Generate a unique lock identifier
		String lockId = "lock" + LockCount;

		// Update seat status and store lock ID in the seatStatusMap
		for (int i = column; i < column + count; i++) {
			String seatKey = generateSeatKey(row, i);
			SeatInfo seatInfo = seatStatusMap.get(seatKey);
			if (seatInfo.getStatus() == SeatStatus.FREE) {
				seatInfo.setStatus(SeatStatus.LOCKED); // Update seat status
				seatInfo.setLockId(lockId); // Store lock ID
				seatInfo.setSeat(seat);
			} else {
				throw new ICinemaLockCinemaException("Seat is not free", new CinemaException());
			}
		}

		LockCount++; // Increment LockCount
		return lockId;
	}

	@Override
	public void unlock(String lockId) throws ICinemaUnlockCinemaException {
		// Check if the lockId is valid
		if (lockId == null || lockId.isEmpty()) {
			throw new ICinemaUnlockCinemaException("Invalid lock identifier.", new CinemaException());
		}

		// Loop through the seatStatusMap to find seats with the given lockId and unlock
		// them
		boolean isLockedFound = false;
		for (Map.Entry<String, SeatInfo> entry : seatStatusMap.entrySet()) {
			SeatInfo seatInfo = entry.getValue();
			if (seatInfo.getLockId() != null && seatInfo.getLockId().equals(lockId)
					&& seatInfo.getStatus() == SeatStatus.LOCKED) {
				// Reset the status to FREE and clear the lockId
				seatInfo.setStatus(SeatStatus.FREE);
				seatInfo.setLockId(null);
				isLockedFound = true;
			}
		}

		if (!isLockedFound) {
			throw new ICinemaUnlockCinemaException("Invalid lock identifier.", new CinemaException());
		}
	}

	@Override
	public void reserve(String lockId) throws ICinemaReserveCinemaException {
		// Check if the lockId is valid
		if (lockId == null || lockId.isEmpty()) {
			throw new ICinemaReserveCinemaException("Invalid lock identifier.", new CinemaException());
		}

		// Loop through the seatStatusMap to find seats with the given lockId and unlock
		// them
		boolean iRecivedFound = false;
		for (Map.Entry<String, SeatInfo> entry : seatStatusMap.entrySet()) {
			SeatInfo seatInfo = entry.getValue();
			if (seatInfo.getLockId() != null && seatInfo.getLockId().equals(lockId)
					&& seatInfo.getStatus() == SeatStatus.LOCKED) {
				// Set seat status to reserved
				seatInfo.setStatus(SeatStatus.RESERVED);
				iRecivedFound = true;
			}
		}

		if (!iRecivedFound) {
			throw new ICinemaReserveCinemaException("Invalid lock identifier.", new CinemaException());
		}

	}

	@Override
	public void buy(String lockId) throws ICinemaBuyCinemaException {
		// Check if the lockId is valid
		if (lockId == null || lockId.isEmpty()) {
			throw new ICinemaBuyCinemaException("Invalid lock identifier.", new CinemaException());
		}

		boolean isSold = false;
		for (Map.Entry<String, SeatInfo> entry : seatStatusMap.entrySet()) {
			SeatInfo seatInfo = entry.getValue();
			if (seatInfo.getLockId() != null && seatInfo.getLockId().equals(lockId)
					&& (seatInfo.getStatus() == SeatStatus.LOCKED || seatInfo.getStatus() == SeatStatus.RESERVED)) {
				// Set seat status to sold
				seatInfo.setStatus(SeatStatus.SOLD);
				isSold = true;
			}
		}

		if (!isSold) {
			throw new ICinemaBuyCinemaException("Invalid lock identifier.", new CinemaException());
		}
	}

}
