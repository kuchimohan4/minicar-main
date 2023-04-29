package com.Deka.MovieCatalogService.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Deka.MovieCatalogService.Entity.MovieCatalog;
import com.Deka.MovieCatalogService.Exception.MovieNotFoundException;
import com.Deka.MovieCatalogService.Repositories.MovieCatalogRepo;

@RestController
@RequestMapping("/movie")
public class MovieCatalogController {

	@Autowired
	private MovieCatalogRepo movieCatalogRepo;

	@GetMapping("/{title}")
	public ResponseEntity<MovieCatalog> getMovieByTitle(@PathVariable String title) {
		MovieCatalog movie = movieCatalogRepo.findByTitle(title);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found for title: " + title);
		}
		return ResponseEntity.ok(movie);
	}

	@PutMapping("/{title}")
	public ResponseEntity<MovieCatalog> updateMovieDetails(@PathVariable String title,
			@RequestBody MovieCatalog updatedMovie) {
		MovieCatalog movie = movieCatalogRepo.findByTitle(title);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found for title: " + title);
		}
		movie.setDescription(updatedMovie.getDescription());
		movie.setGenre(updatedMovie.getGenre());
		movie.setImdbRating(updatedMovie.getImdbRating());
		MovieCatalog savedMovie = movieCatalogRepo.save(movie);
		return ResponseEntity.ok(savedMovie);
	}

	@DeleteMapping("/{title}")
	public ResponseEntity<String> deleteMovieByTitle(@PathVariable String title) {
		MovieCatalog movie = movieCatalogRepo.findByTitle(title);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found for title: " + title);
		}
		movieCatalogRepo.delete(movie);
		return ResponseEntity.ok("Movie with title " + title + " deleted successfully");
	}

	@GetMapping
	public ResponseEntity<List<MovieCatalog>> getAllMovies() {
		List<MovieCatalog> movies = movieCatalogRepo.findAll();
		if (movies.isEmpty()) {
			throw new MovieNotFoundException("No movies found");
		}
		return ResponseEntity.ok(movies);
	}

	@PostMapping
	public ResponseEntity<MovieCatalog> addMovie(@RequestBody MovieCatalog movieCatalog) {
		MovieCatalog savedMovie = movieCatalogRepo.save(movieCatalog);
		return ResponseEntity.created(URI.create("/movie-catalog/" + savedMovie.getTitle())).body(savedMovie);
	}

}
