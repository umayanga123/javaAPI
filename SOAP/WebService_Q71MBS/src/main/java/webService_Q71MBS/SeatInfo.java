package webService_Q71MBS;

import seatreservation.Seat;
import seatreservation.SeatStatus;

public class SeatInfo {
    private SeatStatus status;
    private String lockId;
    private Seat seat;

    public SeatInfo(Seat seat) {
        // Initialize with default values
        this.status = SeatStatus.FREE;
        this.lockId = null;
        this.seat =seat;
    }

    // Getters and setters for status and lockId
    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }
    
    public void setSeat(Seat seat) {
		this.seat = seat;
	}
    
    public Seat getSeat() {
		return seat;
	}
}