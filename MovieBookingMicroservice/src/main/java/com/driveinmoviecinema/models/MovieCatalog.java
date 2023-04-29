package com.driveinmoviecinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MovieCatalog {

	@JsonIgnore
	private String id;
	private String title;
	private String description;
	private String genre;
	private Double imdbRating;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public MovieCatalog(String id, String title, String description, String genre, Double imdbRating) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.imdbRating = imdbRating;
	}

	public MovieCatalog() {
		super();
	}

}
