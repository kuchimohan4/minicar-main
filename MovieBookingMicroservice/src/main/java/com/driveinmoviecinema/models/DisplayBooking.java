package com.driveinmoviecinema.models;

public class DisplayBooking {

	private String TicketConformationId;
	private String carNumber;
	private String movieDate;
	private String moviePlayTime;
	private String ParkingSlotNumber;
	private String TicketStatus;
	private MovieCatalog movieCatalog;
	public String getTicketConformationId() {
		return TicketConformationId;
	}
	public void setTicketConformationId(String ticketConformationId) {
		TicketConformationId = ticketConformationId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}
	public String getMoviePlayTime() {
		return moviePlayTime;
	}
	public void setMoviePlayTime(String moviePlayTime) {
		this.moviePlayTime = moviePlayTime;
	}
	public String getParkingSlotNumber() {
		return ParkingSlotNumber;
	}
	public void setParkingSlotNumber(String parkingSlotNumber) {
		ParkingSlotNumber = parkingSlotNumber;
	}
	public String getTicketStatus() {
		return TicketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		TicketStatus = ticketStatus;
	}
	public MovieCatalog getMovieCatalog() {
		return movieCatalog;
	}
	public void setMovieCatalog(MovieCatalog movieCatalog) {
		this.movieCatalog = movieCatalog;
	}
	public DisplayBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DisplayBooking(String ticketConformationId, String carNumber, String movieDate, String moviePlayTime,
			String parkingSlotNumber, String ticketStatus, MovieCatalog movieCatalog) {
		super();
		TicketConformationId = ticketConformationId;
		this.carNumber = carNumber;
		this.movieDate = movieDate;
		this.moviePlayTime = moviePlayTime;
		ParkingSlotNumber = parkingSlotNumber;
		TicketStatus = ticketStatus;
		this.movieCatalog = movieCatalog;
	}
	
	
	
	
	
	
}
