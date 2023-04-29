package com.Deka.MovieCatalogService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Deka.MovieCatalogService.Entity.MovieCatalog;

public interface MovieCatalogRepo extends JpaRepository<MovieCatalog, String> {

	// custom finder methods
	MovieCatalog findByTitle(String title);

}
