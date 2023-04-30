package com.Deka.MovieSchedule.MovieScheduleService.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Deka.MovieSchedule.MovieScheduleService.Entity.movieSchedule;
import com.Deka.MovieSchedule.MovieScheduleService.exception.ResourceNotFoundException;
import com.Deka.MovieSchedule.MovieScheduleService.exception.unauthorizedException;
import com.Deka.MovieSchedule.MovieScheduleService.services.ScheduleService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/Shows")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	// create
	@PostMapping("/addshow")
	public ResponseEntity<?> createMovieSchedule(@CookieValue(value = "role",defaultValue = "invalid") String role,@Valid @RequestBody movieSchedule movieSchedule,BindingResult bindingResult) throws unauthorizedException  {
		if(role.equals("admin")) {
		if (bindingResult.hasErrors()) {	
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		System.out.println(movieSchedule.getMovieTitle());
		movieSchedule schedule = scheduleService.saveSchedule(movieSchedule);
		return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
		}
		else {
			throw new unauthorizedException("not authorized");
			}
	}

	// single get
	@GetMapping("/title/{movieTitle}")
	public ResponseEntity<?> singleMovieSchedule(@CookieValue(value = "role",defaultValue = "invalid") String role, @PathVariable String movieTitle) throws unauthorizedException  {
		if(role.equals("user")|role.equals("admin")) {
		movieSchedule schedule = scheduleService.getSchedule(movieTitle);
		return ResponseEntity.ok(schedule);
		}
		else {
			throw new unauthorizedException("not authorized");
			}
	}
	
	@GetMapping("/{date}")
	public ResponseEntity<?> singleMovieScheduleByDate(@CookieValue(value = "role",defaultValue = "invalid") String role, @PathVariable String date) throws unauthorizedException{
		if(role.equals("user")|role.equals("admin")) {
		List<movieSchedule> schedule = scheduleService.getScheduleByDate(date);
		return ResponseEntity.ok(schedule);
		}
		else {
			throw new unauthorizedException("not authorized");
			}
		
		
	}

	@GetMapping("allMovies")
	public ResponseEntity<List<movieSchedule>> getAllSchedule(@CookieValue(value = "role",defaultValue = "invalid") String role) throws unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
		List<movieSchedule> allSchedule = scheduleService.getAllSchedule();
		return ResponseEntity.ok(allSchedule);

		}
		else {
			throw new unauthorizedException("not authorized");
			}
	}
	
	@GetMapping("/{date}/{time}")
	public movieSchedule getScheduleBydateAndTime(@CookieValue(value = "role",defaultValue = "invalid") String role,@PathVariable("date") String date,@PathVariable("time") String time) throws unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
		return scheduleService.getScheduleBydateAndTime(date,time);

		}
		else {
			throw new unauthorizedException("not authorized");
			}
	}
	
	@ExceptionHandler
	public ResponseEntity<?> exceptionHandeler(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(unauthorizedException.class)
	public ResponseEntity<?> unauthorizedexceptionHandler(unauthorizedException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}

}
