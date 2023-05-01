package com.driveinmoviecinema.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class BookingDetails {
	
	
	@Id
	private String ticketConformationId;
	@Size(min = 5,max = 11)
	private String carNumber;
	@Size(min = 3)
	private String movieTitle;
	@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date should be in dd-MM-yyyy format")
	private String movieDate;
	private String moviePlayTime;
	@Size(max = 3,message = "INVALID PARING SLOT")
	private String ParkingSlotNumber;
	@JsonIgnore
	private String TicketStatus;
	private String userID;
	
	private double amount;
	
	public String getTicketConformationId() {
		return ticketConformationId;
	}
	public void setTicketConformationId(String ticketConformationId) {
		ticketConformationId = ticketConformationId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
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
	public BookingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public BookingDetails(String ticketConformationId, @Size(min = 5, max = 11) String carNumber,
			@Size(min = 3) String movieTitle,
			@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date should be in dd-MM-yyyy format") String movieDate,
			String moviePlayTime, @Size(max = 3, message = "INVALID PARING SLOT") String parkingSlotNumber,
			String ticketStatus, String userID, double amount) {
		super();
		this.ticketConformationId = ticketConformationId;
		this.carNumber = carNumber;
		this.movieTitle = movieTitle;
		this.movieDate = movieDate;
		this.moviePlayTime = moviePlayTime;
		ParkingSlotNumber = parkingSlotNumber;
		TicketStatus = ticketStatus;
		this.userID = userID;
		this.amount = amount;
	}
	public void genrateConfmId() {
		
		this.ticketConformationId=UUID.randomUUID().toString().split("-",5)[0];
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	

}
