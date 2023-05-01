package com.driveinmoviecinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.driveinmoviecinema.models.BookingDetails;

@Repository
public interface BookingDetailsRepositry extends JpaRepository<BookingDetails, String> {

	List<BookingDetails> findByticketConformationIdAndUserID(String id,String user);

	List<BookingDetails> findByUserID(String user);
	
}
