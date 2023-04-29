package com.driveinmoviecinema.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.driveinmoviecinema.models.MovieCatalog;

@FeignClient(name = "MOVIE-CATALOG-SERVICE")
public interface movieCatalogServiceProxy {

	@GetMapping("/movie/{title}")
	public ResponseEntity<MovieCatalog> getMovieByTitle(@PathVariable String title);

}
