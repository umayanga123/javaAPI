package webService_Q71MBS;
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

@WebService(name = "CinemaService", 
             portName = "ICinema_HttpSoap11_Port", 
             targetNamespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", 
             endpointInterface = "seatreservation.ICinema", 
             wsdlLocation = "WEB-INF/wsdl/SeatReservation.wsdl")
public class Cinema implements ICinema {

	private ArrayOfSeat allSeats;
    private boolean[][] seatStatus;
    private static final int SEAT_NOT_AVAILABLE = 0;
    private static final int SEAT_AVAILABLE = 1;

    @Override
    public void init(int rows, int columns) throws ICinemaInitCinemaException {
        if (rows <= 0 || columns <= 0) {
            throw new ICinemaInitCinemaException("Invalid rows or columns provided.",new CinemaException());
        }
        allSeats = new ArrayOfSeat();
        seatStatus = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Seat seat = new Seat();
                seat.setRow(Integer.toString(i));
                seat.setColumn(Integer.toString(j));
                allSeats.getSeat().add(seat);
                seatStatus[i][j] = true; // Seat available
            }
        }
    }

    @Override
    public ArrayOfSeat getAllSeats() throws ICinemaGetAllSeatsCinemaException {
    	 if (allSeats == null) {
    	        throw new ICinemaGetAllSeatsCinemaException("Cinema has not been initialized.", new CinemaException());
    	    }

    	    ArrayOfSeat modifiedSeats = new ArrayOfSeat();

    	    int numRows = seatStatus.length;
    	    int numCols = seatStatus[0].length;

    	    // Start with ASCII value for 'A'
    	    int currentRowLabel = 65; // 'A'

    	    for (int i = 0; i < numRows; i++) {
    	        char rowLabel = (char) currentRowLabel;
    	        for (int j = 0; j < numCols; j++) {
    	            Seat seat = new Seat();
    	            // Set row label as character representation
    	            seat.setRow(Character.toString(rowLabel));
    	            // Set column number
    	            seat.setColumn(Integer.toString(j + 1)); // Adding 1 to start from 1
    	            modifiedSeats.getSeat().add(seat);
    	        }
    	        // Increment ASCII value for next row label
    	        currentRowLabel++;
    	    }

    	    return modifiedSeats;
    }

    @Override
    public SeatStatus getSeatStatus(Seat seat) throws ICinemaGetSeatStatusCinemaException {
		return null;
        
    }

    @Override
    public String lock(Seat seat, int count) throws ICinemaLockCinemaException {
		return null;
      
    }

    @Override
    public void unlock(String lockId) throws ICinemaUnlockCinemaException {
        // Here you would implement logic to unlock seats based on lockId
    }

    @Override
    public void reserve(String lockId) throws ICinemaReserveCinemaException {
        // Here you would implement logic to reserve seats based on lockId
    }

    @Override
    public void buy(String lockId) throws ICinemaBuyCinemaException {
        // Here you would implement logic to buy reserved seats based on lockId
    }

}
