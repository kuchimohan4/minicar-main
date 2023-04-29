package com.driveinmoviecinema.models;



import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ParkingSlot {
	
	@JsonIgnore
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	@Id
	private int id;
	@JsonIgnore
	@Size(max = 3,message = "INVALID PARKING SLOT")
	private String slot;
	@Size(min = 3,message = "Movie title Should be minimum of size 3")
	private String movieTitle;
//	@Future(message = "date should be in future")
	@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date should be in dd-MM-yyyy format")
	private String date;
	private String playTime;
	@JsonIgnore
	private boolean avliable; 
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public ParkingSlot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public boolean isAvliable() {
		return avliable;
	}
	public void setAvliable(boolean avliable) {
		this.avliable = avliable;
	}
	public ParkingSlot(int id, String slot, String movieTitle, String date, String playTime, boolean avliable) {
		super();
		this.id = id;
		this.slot = slot;
		this.movieTitle = movieTitle;
		this.date = date;
		this.playTime = playTime;
		this.avliable = avliable;
	}
	
	
}
