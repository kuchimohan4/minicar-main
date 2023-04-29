package com.driveinmoviecinema.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.driveinmoviecinema.models.movieSchedule;

@FeignClient(name ="MOVIE-SCHEDULE-SERVICE")
public interface movieScheduleServiceProxy {
	
	@GetMapping("/Shows/{date}/{time}")
	public movieSchedule getScheduleBydateAndTime(@PathVariable("date") String date,@PathVariable("time") String time) throws RuntimeException;
}
