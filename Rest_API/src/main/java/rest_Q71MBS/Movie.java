package rest_Q71MBS;

import java.util.Arrays;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movie")
public class Movie {
	private String title;
	private int year;
	private String director;
	private List<String> actor;
	
	 // Default constructor required for JAXB
    public Movie() {}

    public Movie(String title, int year, String director, String[] actorNames) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.actor = Arrays.asList(actorNames);
    }

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@XmlElement
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@XmlElement
	public List<String> getActor() {
		return actor;
	}

	public void setActor(List<String> actor) {
		this.actor = actor;
	}
}
