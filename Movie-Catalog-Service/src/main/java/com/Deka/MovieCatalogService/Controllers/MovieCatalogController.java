package com.Deka.MovieCatalogService.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Deka.MovieCatalogService.Entity.MovieCatalog;
import com.Deka.MovieCatalogService.Exception.MovieNotFoundException;
import com.Deka.MovieCatalogService.Exception.unauthorizedException;
import com.Deka.MovieCatalogService.Repositories.MovieCatalogRepo;

@RestController
@RequestMapping("/movie")
public class MovieCatalogController {

	@Autowired
	private MovieCatalogRepo movieCatalogRepo;

	@GetMapping("/{title}")
	public ResponseEntity<MovieCatalog> getMovieByTitle(@CookieValue(value = "role",defaultValue = "invalid") String role,@PathVariable String title) throws unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
		MovieCatalog movie = movieCatalogRepo.findByTitle(title);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found for title: " + title);
		}
		return ResponseEntity.ok(movie);
		}
		else {
			throw new unauthorizedException("not authorized");
		}
	}

	@PutMapping("/{title}")
	public ResponseEntity<MovieCatalog> updateMovieDetails(@CookieValue(value = "role",defaultValue = "invalid") String role,@PathVariable String title,
			@RequestBody MovieCatalog updatedMovie) throws unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
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
		else {
			throw new unauthorizedException("not authorized");
		}
		
	}

	@DeleteMapping("/{title}")
	public ResponseEntity<String> deleteMovieByTitle(@CookieValue(value = "role",defaultValue = "invalid") String role,@PathVariable String title) throws unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
			
		MovieCatalog movie = movieCatalogRepo.findByTitle(title);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found for title: " + title);
		}
		movieCatalogRepo.delete(movie);
		return ResponseEntity.ok("Movie with title " + title + " deleted successfully");
		}
		else {
			throw new unauthorizedException("not authorized");
		}
		
		}

	@GetMapping
	public ResponseEntity<?> getAllMovies(@CookieValue(value = "role",defaultValue = "invalid") String role) throws unauthorizedException {
		System.out.println(role);
		if(role.equals("user")|role.equals("admin")) {
		
		System.out.println(role+" user");
		List<MovieCatalog> movies = movieCatalogRepo.findAll();
		if (movies.isEmpty()) {
			throw new MovieNotFoundException("No movies found");
		}
		return ResponseEntity.ok(movies);
		}
		else {
			throw new unauthorizedException("not authorized");
		}
	}

	@PostMapping
	public ResponseEntity<MovieCatalog> addMovie(@RequestBody MovieCatalog movieCatalog) {
		
		MovieCatalog savedMovie = movieCatalogRepo.save(movieCatalog);
		return ResponseEntity.created(URI.create("/movie-catalog/" + savedMovie.getTitle())).body(savedMovie);
	}

}
