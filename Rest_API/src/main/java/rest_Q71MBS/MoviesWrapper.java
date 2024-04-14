package rest_Q71MBS;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movies")
public class MoviesWrapper {
    private List<Movie> movies;

    public MoviesWrapper() {
    }

    public MoviesWrapper(List<Movie> movies) {
        this.movies = movies;
    }

    @XmlElement(name = "movie")
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}