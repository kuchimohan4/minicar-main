package com.Deka.MovieSchedule.MovieScheduleService.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Deka.MovieSchedule.MovieScheduleService.Entity.movieSchedule;
import com.Deka.MovieSchedule.MovieScheduleService.exception.ResourceNotFoundException;
import com.Deka.MovieSchedule.MovieScheduleService.services.ScheduleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Shows")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	// create
	@PostMapping("/addshow")
	public ResponseEntity<?> createMovieSchedule(@Valid @RequestBody movieSchedule movieSchedule,BindingResult bindingResult)  {
		
		if (bindingResult.hasErrors()) {	
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		System.out.println(movieSchedule.getMovieTitle());
		movieSchedule schedule = scheduleService.saveSchedule(movieSchedule);
		return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
	}

	// single get
	@GetMapping("/title/{movieTitle}")
	public ResponseEntity<?> singleMovieSchedule( @PathVariable String movieTitle)  {
		movieSchedule schedule = scheduleService.getSchedule(movieTitle);
		return ResponseEntity.ok(schedule);
	}
	
	@GetMapping("/{date}")
	public ResponseEntity<?> singleMovieScheduleByDate( @PathVariable String date){
		List<movieSchedule> schedule = scheduleService.getScheduleByDate(date);
		return ResponseEntity.ok(schedule);
	}

	@GetMapping("allMovies")
	public ResponseEntity<List<movieSchedule>> getAllSchedule() {
		List<movieSchedule> allSchedule = scheduleService.getAllSchedule();
		return ResponseEntity.ok(allSchedule);

	}
	
	@GetMapping("/{date}/{time}")
	public movieSchedule getScheduleBydateAndTime(@PathVariable("date") String date,@PathVariable("time") String time) {
		
		return scheduleService.getScheduleBydateAndTime(date,time);

	}
	
	@ExceptionHandler
	public ResponseEntity<?> exceptionHandeler(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}

}
