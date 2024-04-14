package cinemasocket;

public class Seat {
	private SeatStatus status;
	private String lockId;

	public Seat(SeatStatus status) {
		this.status = status;
	}

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
}
