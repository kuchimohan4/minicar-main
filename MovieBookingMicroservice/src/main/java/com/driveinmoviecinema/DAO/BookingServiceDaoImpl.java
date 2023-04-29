package com.driveinmoviecinema.DAO;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.driveinmoviecinema.exception.noSlotsFoundException;
import com.driveinmoviecinema.models.BookingDetails;
import com.driveinmoviecinema.models.ParkingSlot;
import com.driveinmoviecinema.models.avaliableParkingSlots;
import com.driveinmoviecinema.repository.BookingDetailsRepositry;
import com.driveinmoviecinema.repository.ParkingSlotRepository;

@Component
public class BookingServiceDaoImpl implements BookingServiceDao {
	
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;
	
	@Autowired
	private BookingDetailsRepositry bookingDetailsRepositry;

	@Override
	public avaliableParkingSlots parkingSlotAvaliability(ParkingSlot parkingSlot)throws noSlotsFoundException {
		List<ParkingSlot> parkingSlots=parkingSlotRepository.findByDateAndPlayTimeAndAvliable(parkingSlot.getDate(), parkingSlot.getPlayTime(),true);
		if(parkingSlots.size()==0) {
			
			throw new noSlotsFoundException("Error working request posible resons might no slots avaliable for "+parkingSlot.getMovieTitle()+" movie for time "+parkingSlot.getPlayTime()+" or in valid inputs");
		}
		List<String> avaliableslots=new ArrayList<>();
		for(ParkingSlot slot:parkingSlots) {
			
			avaliableslots.add(slot.getSlot());
			
		}
		
		return new avaliableParkingSlots(parkingSlot.getMovieTitle(), parkingSlot.getDate(), parkingSlot.getPlayTime(), avaliableslots);
		
	}

	@Override
	public BookingDetails BookTicket(BookingDetails bookingDetails)throws noSlotsFoundException {
		
		List<ParkingSlot> parkinglotDetails = parkingSlotRepository.findBySlotAndDateAndPlayTime(bookingDetails.getParkingSlotNumber(),bookingDetails.getMovieDate(), bookingDetails.getMoviePlayTime());
		
		if(parkinglotDetails.size()==0) {
			throw new noSlotsFoundException("Error working with request posible resons might be no slots avaliable  or in valid inputs");
			
		}
		
		if(!parkinglotDetails.get(0).isAvliable()) {
			
			throw new noSlotsFoundException("PARKING SLOT ALREADY BOOKED");
		}
		
		bookingDetails.genrateConfmId();
		bookingDetails.setTicketStatus("Booked");
		ParkingSlot parkingSlot=parkinglotDetails.get(0);
		parkingSlot.setAvliable(false);
		parkingSlotRepository.save(parkingSlot);
		System.out.println(bookingDetails);
		return bookingDetailsRepositry.save(bookingDetails);
	}

	@Override
	public BookingDetails showBookingDetails(String id) throws noSlotsFoundException {
		
		BookingDetails bookingDetailsOfuser= bookingDetailsRepositry.findById(id).orElse(new BookingDetails());
		
		
		if(bookingDetailsOfuser.getTicketConformationId()==null) {
			throw new noSlotsFoundException("NO BOOKING WITH BOOKING ID "+id);
		}
		
		return bookingDetailsOfuser;
	}

	@Override
	public BookingDetails cancelBooking(String id) throws noSlotsFoundException {
		
		BookingDetails bookingDetailsOfuser= bookingDetailsRepositry.findById(id).orElse(new BookingDetails());
		if(bookingDetailsOfuser.getTicketConformationId()==null) {
			throw new noSlotsFoundException("NO BOOKING WITH BOOKING ID "+id);
		}
		bookingDetailsOfuser.setTicketStatus("Canceled");
		List<ParkingSlot> parkinglotDetails = parkingSlotRepository.findBySlotAndDateAndPlayTime(bookingDetailsOfuser.getParkingSlotNumber(),bookingDetailsOfuser.getMovieDate(), bookingDetailsOfuser.getMoviePlayTime());
		ParkingSlot parkingSlot=parkinglotDetails.get(0);
		parkingSlot.setAvliable(true);
		parkingSlotRepository.save(parkingSlot);
		return bookingDetailsRepositry.save(bookingDetailsOfuser);
	}

}
