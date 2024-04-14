package cinemasocket;

import jakarta.json.Json;
import jakarta.json.JsonObject;

/***
 * 
 * Build server side response messages.
 * JSON object encoder
 * 
 */
public class MessageBuilder {

	public static String initRoom(int rows, int columns) {
		JsonObject jsonObject = Json.createObjectBuilder().add("type", "roomSize").add("rows", rows)
				.add("columns", columns).build();
		return jsonObject.toString();
	}

	public static String seatStatus(int row, int column, String status) {
		JsonObject jsonObject = Json.createObjectBuilder().add("type", "seatStatus").add("row", row)
				.add("column", column).add("status", status).build();
		return jsonObject.toString();
	}

	public static String lockResult(String lockId) {
		JsonObject jsonObject = Json.createObjectBuilder().add("type", "lockResult").add("lockId", lockId).build();
		return jsonObject.toString();
	}

	public static String error(String message) {
		JsonObject jsonObject = Json.createObjectBuilder().add("type", "error").add("message", message).build();
		return jsonObject.toString();
	}

}
