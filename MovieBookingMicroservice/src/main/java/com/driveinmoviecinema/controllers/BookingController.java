package com.driveinmoviecinema.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.driveinmoviecinema.Service.BookingService;
import com.driveinmoviecinema.exception.noSlotsFoundException;
import com.driveinmoviecinema.exception.unauthorizedException;
import com.driveinmoviecinema.models.BookingDetails;
import com.driveinmoviecinema.models.DisplayBooking;
import com.driveinmoviecinema.models.MovieCatalog;
import com.driveinmoviecinema.models.ParkingSlot;
import com.driveinmoviecinema.models.movieSchedule;
import com.driveinmoviecinema.repository.movieCatalogServiceProxy;
import com.driveinmoviecinema.repository.movieScheduleServiceProxy;

import io.swagger.v3.oas.annotations.Hidden;
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
	public ResponseEntity<?>   parkingSlotAvaliability(@CookieValue(value = "role",defaultValue = "invalid") String role,@Valid @RequestBody ParkingSlot parkingSlot,BindingResult bindingResult) throws noSlotsFoundException, unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
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
		else {
			throw new unauthorizedException("not authorized");
			}
		
	}
	@PostMapping("/BookTicket")
	public ResponseEntity<?>  BookTicket(@CookieValue(value = "role",defaultValue = "invalid") String role,@Valid @RequestBody BookingDetails bookingDetails,BindingResult bindingResult)throws noSlotsFoundException, unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
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
		else {
			throw new unauthorizedException("not authorized");
			}
		
	}
	@GetMapping("/viewBookingDetails/{id}")
	public ResponseEntity<?> showBookingDetails(@CookieValue(value = "role",defaultValue = "invalid") String role,@PathVariable String id)throws noSlotsFoundException, unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
		BookingDetails bDetails=bookingService.showBookingDetails(id);
		ResponseEntity<MovieCatalog> movieResponseEntity=catalogProxy.getMovieByTitle(bDetails.getMovieTitle());
		
		MovieCatalog movieDetails=movieResponseEntity.getBody();
		DisplayBooking displayBooking=new DisplayBooking(bDetails.getTicketConformationId(), bDetails.getCarNumber(), bDetails.getMovieDate(), bDetails.getMoviePlayTime(), bDetails.getParkingSlotNumber(), bDetails.getTicketStatus(), movieDetails);
		
		
		
		return new ResponseEntity<>(displayBooking, HttpStatus.OK) ;	
		}
		else {
			throw new unauthorizedException("not authorized");
			}
	}
	@DeleteMapping("/CancelBooking/{id}")
	public ResponseEntity<?> cancelBooking( @CookieValue(value = "role",defaultValue = "invalid")  String role,@PathVariable String id) throws noSlotsFoundException, unauthorizedException {
		if(role.equals("user")|role.equals("admin")) {
		
		return new ResponseEntity<>(bookingService.cancelBooking(id), HttpStatus.OK) ;
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
	public ResponseEntity<?> handleunauthorizedException(Exception ex) {
RedirectView  redirectView=new RedirectView("/login");
		
		return new ResponseEntity<>(redirectView, HttpStatus.UNAUTHORIZED);
	}
	

}
