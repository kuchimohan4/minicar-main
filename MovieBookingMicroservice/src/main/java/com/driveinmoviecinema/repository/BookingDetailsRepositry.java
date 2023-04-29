package com.driveinmoviecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driveinmoviecinema.models.BookingDetails;

@Repository
public interface BookingDetailsRepositry extends JpaRepository<BookingDetails, String> {

	
}
