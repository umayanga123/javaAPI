package cinemasocket;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/cinema")
public class CinemaEndpoint {

	private static int rowCount = 0;
	private static int colCount = 0;
	private static Map<String, Seat> seats = new HashMap<>();

	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void open(Session session) {
		sessions.add(session);
		System.out.println("WebSocket opened: " + session.getId());
	}

	@OnClose
	public void close(Session session) {
		sessions.remove(session);
		System.out.println("WebSocket closed: " + session.getId());
	}

	@OnError
	public void error(Throwable t) {
		System.out.println("WebSocket error: " + t.getMessage());
	}

	@OnMessage
	public void handleMessage(String message, Session session) throws IOException {
		System.out.println("Received message from client: " + message);
		try {
			// Parse the JSON message to extract the message type
	        JsonObject jsonMessage = Json.createReader(new StringReader(message)).readObject();
	        String messageType = jsonMessage.getString("type");
			switch (MessageType.valueOf(messageType)) {
			case initRoom:
				initRoom(message, session);
				break;
			case getRoomSize:
				getRoomSize(session);
				break;
			case updateSeats:
				updateSeats(session);
				break;
			case lockSeat:
				lockSeat(message, session);
				break;
			case unlockSeat:
				unlockSeat(message, session);
				break;
			case reserveSeat:
				reserveSeat(message, session);
				break;
			default:
				sendError(session, "Unknown operation");
			}
		} catch (IllegalArgumentException e) {
			sendError(session, "Invalid operation");
		}
	}

	private void initRoom(String message, Session session) throws IOException {
		try {
			int rows = JsonUtils.getIntFromJson(message, "rows");
			int columns = JsonUtils.getIntFromJson(message, "columns");
			if (rows <= 0 || columns <= 0) {
				sendError(session, "Rows and columns must be positive integers");
				return;
			}
			rowCount = rows;
			colCount = columns;
			seats.clear();
			for (int i = 1; i <= rows; i++) {
				for (int j = 1; j <= columns; j++) {
					seats.put(getSeatId(i, j), new Seat(SeatStatus.free));
				}
			}
			sendResponse(session, MessageBuilder.initRoom(rows, columns));
			//broadcast init change to all clients
			broadcastInitToAllclient(rows, columns);
		} catch (IllegalArgumentException e) {
			sendError(session, "Invalid room initialization parameters");
		}
	}

	private void getRoomSize(Session session) throws IOException {
		sendResponse(session, MessageBuilder.initRoom(rowCount, colCount));
	}

	private void updateSeats(Session session) throws IOException {
		for (Map.Entry<String, Seat> entry : seats.entrySet()) {
			Seat seat = entry.getValue();
			sendResponse(session, MessageBuilder.seatStatus(Integer.parseInt(entry.getKey().split("_")[0]),
					Integer.parseInt(entry.getKey().split("_")[1]), seat.getStatus().toString()));
		}
	}

	private void lockSeat(String message, Session session) throws IOException {
		int row = JsonUtils.getIntFromJson(message, "row");
		int column = JsonUtils.getIntFromJson(message, "column");
		String seatId = getSeatId(row, column);
		Seat seat = seats.get(seatId);
		if (seat == null || seat.getStatus() != SeatStatus.free) {
			sendError(session, "Seat is not free");
			return;
		}
		String lockId = UUID.randomUUID().toString();
		seat.setStatus(SeatStatus.locked);
		seat.setLockId(lockId);
		sendResponse(session, MessageBuilder.lockResult(lockId));
		sendResponse(session, MessageBuilder.seatStatus(row, column, seat.getStatus().toString()));
		broadcastSeatStatus(row, column, seat.getStatus().toString());
	}

	private void unlockSeat(String message, Session session) throws IOException {
		String lockId = JsonUtils.getStringFromJson(message, "lockId");
		for (Map.Entry<String, Seat> entry : seats.entrySet()) {
			Seat seat = entry.getValue();
			if (seat.getLockId() != null && seat.getLockId().equals(lockId)) {
				seat.setStatus(SeatStatus.free);
				sendResponse(session, MessageBuilder.seatStatus(Integer.parseInt(entry.getKey().split("_")[0]),
						Integer.parseInt(entry.getKey().split("_")[1]), seat.getStatus().toString()));
				broadcastSeatStatus(Integer.parseInt(entry.getKey().split("_")[0]),
						Integer.parseInt(entry.getKey().split("_")[1]), seat.getStatus().toString());
				return;
			}
		}
		sendError(session, "Invalid lock ID");
	}

	private void reserveSeat(String message, Session session) throws IOException {
		String lockId = JsonUtils.getStringFromJson(message, "lockId");
		for (Map.Entry<String, Seat> entry : seats.entrySet()) {
			Seat seat = entry.getValue();
			if (seat.getLockId() != null && seat.getLockId().equals(lockId)) {
				seat.setStatus(SeatStatus.reserved);
				sendResponse(session, MessageBuilder.seatStatus(Integer.parseInt(entry.getKey().split("_")[0]),
						Integer.parseInt(entry.getKey().split("_")[1]), seat.getStatus().toString()));
				broadcastSeatStatus(Integer.parseInt(entry.getKey().split("_")[0]),
						Integer.parseInt(entry.getKey().split("_")[1]), seat.getStatus().toString());
				return;
			}
		}
		sendError(session, "Invalid lock ID");
	}

	private void broadcastSeatStatus(int row, int column, String status) throws IOException {
		for (Session s : sessions) {
			sendResponse(s, MessageBuilder.seatStatus(row, column, status));
		}
	}
	
	private void broadcastInitToAllclient(int rows, int columns) throws IOException {
		for (Session s : sessions) {
			sendResponse(s, MessageBuilder.initRoom(rows, columns));
		}
	}

	private void sendResponse(Session session, String responseJson) throws IOException {
		session.getBasicRemote().sendText(responseJson);
	}

	private void sendError(Session session, String message) throws IOException {
		sendResponse(session, MessageBuilder.error(message));
	}

	private String getSeatId(int row, int column) {
		return row + "_" + column;
	}
}