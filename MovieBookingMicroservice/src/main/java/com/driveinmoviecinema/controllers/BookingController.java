package com.driveinmoviecinema.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.driveinmoviecinema.Service.BookingService;
import com.driveinmoviecinema.exception.noSlotsFoundException;
import com.driveinmoviecinema.models.BookingDetails;
import com.driveinmoviecinema.models.DisplayBooking;
import com.driveinmoviecinema.models.MovieCatalog;
import com.driveinmoviecinema.models.ParkingSlot;
import com.driveinmoviecinema.models.movieSchedule;
import com.driveinmoviecinema.repository.movieCatalogServiceProxy;
import com.driveinmoviecinema.repository.movieScheduleServiceProxy;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/Booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private movieScheduleServiceProxy proxy;
	
	@Autowired
	private movieCatalogServiceProxy catalogProxy;
	
	@PostMapping("/ParkingSlots")
	public ResponseEntity<?>   parkingSlotAvaliability(@Valid @RequestBody ParkingSlot parkingSlot,BindingResult bindingResult) throws noSlotsFoundException {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		movieSchedule movieSchedule=proxy.getScheduleBydateAndTime(parkingSlot.getDate(), parkingSlot.getPlayTime());
		
		if(!(movieSchedule.getMovieTitle().equals(parkingSlot.getMovieTitle()))){
			
			throw new RuntimeException(parkingSlot.getMovieTitle()+" is not sheduled on "+parkingSlot.getDate()+" at time slot "+parkingSlot.getPlayTime());
		}
		
		
		return new ResponseEntity<>(bookingService.parkingSlotAvaliability(parkingSlot),HttpStatus.OK);
		
	}
	@PostMapping("/BookTicket")
	public ResponseEntity<?>  BookTicket(@Valid @RequestBody BookingDetails bookingDetails,BindingResult bindingResult)throws noSlotsFoundException {
		
		if (bindingResult.hasErrors()) {	
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		movieSchedule movieSchedule=proxy.getScheduleBydateAndTime(bookingDetails.getMovieDate(), bookingDetails.getMoviePlayTime());
		
		if(!(movieSchedule.getMovieTitle().equals(bookingDetails.getMovieTitle()))){
			
			throw new RuntimeException(bookingDetails.getMovieTitle()+" is not sheduled on "+bookingDetails.getMovieDate()+" at time slot "+bookingDetails.getMoviePlayTime());
		}
		String parking_slot=bookingDetails.getParkingSlotNumber();
		double amount= (parking_slot.contains("A")|parking_slot.contains("B"))? movieSchedule.getPriceForFirstTwoRows():((parking_slot.contains("C")|parking_slot.contains("D"))? movieSchedule.getPriceForMiddleTwoRows():movieSchedule.getPriceForLastTwoRows());
		
		System.out.println(amount);
		bookingDetails.setAmount((int) amount);
		
		return new ResponseEntity<>(bookingService.BookTicket(bookingDetails),HttpStatus.ACCEPTED);
	}
	@GetMapping("/viewBookingDetails/{id}")
	public DisplayBooking showBookingDetails(@PathVariable String id)throws noSlotsFoundException {
		
		BookingDetails bDetails=bookingService.showBookingDetails(id);
		ResponseEntity<MovieCatalog> movieResponseEntity=catalogProxy.getMovieByTitle(bDetails.getMovieTitle());
		
		MovieCatalog movieDetails=movieResponseEntity.getBody();
		DisplayBooking displayBooking=new DisplayBooking(bDetails.getTicketConformationId(), bDetails.getCarNumber(), bDetails.getMovieDate(), bDetails.getMoviePlayTime(), bDetails.getParkingSlotNumber(), bDetails.getTicketStatus(), movieDetails);
		
		
		
		return displayBooking;	
	}
	@DeleteMapping("/CancelBooking/{id}")
	public BookingDetails cancelBooking(@PathVariable String id) throws noSlotsFoundException {
		
		
		return bookingService.cancelBooking(id);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> exceptionHandeler(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	

}
