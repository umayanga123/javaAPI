package cinemasocket;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/***
 * JSON object decoder
 */
public class JsonUtils {

	public static int getIntFromJson(String jsonString, String key) {
		// Parse the JSON string into a JsonObject
		// Retrieve the integer value associated with the provided key
		JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
		return jsonObject.getInt(key);
	}

	public static String getStringFromJson(String jsonString, String key) {
		// Parse the JSON string into a JsonObject
		JsonObject jsonObject = parseJsonObject(jsonString);
		// Retrieve the string value associated with the provided key
		return jsonObject.getString(key);
	}

	private static JsonObject parseJsonObject(String jsonString) {
		// Create a JsonReader to read the JSON string
		try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
			// Parse the JSON string into a JsonObject
			JsonObject jsonObject = jsonReader.readObject();
			return jsonObject;
		} catch (Exception e) {
			// Handle parsing exceptions
			e.printStackTrace();
			return null;
		}
	}

}
