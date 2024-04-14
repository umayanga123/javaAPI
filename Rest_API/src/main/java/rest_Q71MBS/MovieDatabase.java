package rest_Q71MBS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class MovieDatabase implements IMovieDatabase {

	// Static list to store movies
	private static List<Movie> movies = new ArrayList<>();

	@Override
	public MoviesWrapper movies() {
		return new MoviesWrapper(movies);
	}

	@Override
	public Response getMovieById(int id) {
		// Check if the index is within the bounds of the list
		if (id >= 0 && id < movies.size()) {
			// Call the service to retrieve the movie by ID
			Movie movie = movies.get(id);

			// Return the movie with a 200 OK status code
			return Response.ok(movie).build();
		} else {
			// If the index is out of bounds, return a 404 Not Found status code
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@Override
	public Response addMovie(Movie movie) {
		// Add the movie to the database
		movies.add(movie);

		// Get the index of the newly added movie
		int index = movies.size() - 1;

		// Create a JSON object with the movie id
		// Construct the JSON response manually
		String jsonResponse = "{\"id\": " + index + "}";

		// Return the JSON string as the response body
		return Response.ok(jsonResponse).build();
	}

	@Override
	public Response updateMovie(int id, Movie movie) {
		// Check if the movie with the given ID exists
		if (id >= 0 && id < movies.size()) {
			// Update the existing movie with the new data
			movies.set(id, movie);

			// Return a response with status 200 OK
			return Response.ok().build();
		} else {
			// Insert the new movie to the database
			movies.add(movie);

			// Return a response with status 201 Created
			return Response.status(Status.CREATED).build();
		}
	}

	// Method to delete a movie from the database
	@Override
	public Response deleteMovie(int id) {
		// Check if the index is within the bounds of the list
		if (id >= 0 && id < movies.size()) {
			// Call the service to retrieve the movie by ID
			movies.remove(id);

			// Return the movie with a 200 OK status code
			return Response.status(Status.OK).build();
		} else {
			// If the index is out of bounds, return a 404 Not Found status code
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@Override
	public Response findMoviesByYearAndOrderByField(int year, String orderBy) {
		// Filter movies by year
		List<Integer> ids = movies.stream().filter(movie -> movie.getYear() == year).map(movies::indexOf)
				.collect(Collectors.toList());

		// Sort the list of IDs based on the specified field
		switch (orderBy.toLowerCase()) {
		case "title":
			ids.sort((id1, id2) -> movies.get(id1).getTitle().compareTo(movies.get(id2).getTitle()));
			break;
		case "director":
			ids.sort((id1, id2) -> movies.get(id1).getDirector().compareTo(movies.get(id2).getDirector()));
			break;
		default:
			// Default sorting by movie title
			ids.sort((id1, id2) -> movies.get(id1).getTitle().compareTo(movies.get(id2).getTitle()));
			break;
		}

		// Create the XML response
		StringBuilder xmlResponse = new StringBuilder("<movies>");
		for (Integer id : ids) {
			xmlResponse.append("<id>").append(id).append("</id>");
		}
		xmlResponse.append("</movies>");

		// Return the XML response
		return Response.ok(xmlResponse.toString()).build();
	}

}
