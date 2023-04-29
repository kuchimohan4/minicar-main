package com.driveinmoviecinema.DAO;

import com.driveinmoviecinema.exception.noSlotsFoundException;
import com.driveinmoviecinema.models.BookingDetails;
import com.driveinmoviecinema.models.ParkingSlot;
import com.driveinmoviecinema.models.avaliableParkingSlots;

public interface BookingServiceDao {
	
	public avaliableParkingSlots parkingSlotAvaliability(ParkingSlot parkingSlot) throws noSlotsFoundException;

	public BookingDetails BookTicket(BookingDetails bookingDetails) throws noSlotsFoundException;

	public BookingDetails showBookingDetails(String id) throws noSlotsFoundException;

	public BookingDetails cancelBooking(String id) throws noSlotsFoundException;

}
