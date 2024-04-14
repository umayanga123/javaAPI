package rest_Q71MBS;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("MovieDatabase")
public interface IMovieDatabase {

	@GET
	@Path("movies")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	MoviesWrapper movies();

	@GET
	@Path("movies/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response getMovieById(@PathParam("id") int id);

	@GET
	@Path("movies/find")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response findMoviesByYearAndOrderByField(@QueryParam("year") int year, @QueryParam("orderby") String orderBy);

	@POST
	@Path("movies")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addMovie(Movie movie);

	@PUT
	@Path("movies/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateMovie(@PathParam("id") int id, Movie movie);

	@DELETE
	@Path("movies/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response deleteMovie(@PathParam("id") int id);

}
