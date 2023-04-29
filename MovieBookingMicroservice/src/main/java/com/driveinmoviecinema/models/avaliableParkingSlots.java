package com.driveinmoviecinema.models;

import java.util.Date;
import java.util.List;

public class avaliableParkingSlots {

	private String movieTitle;
	private String date;
	private String PlayTime;
	private List<String> avaliableSlots;
	
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
		return PlayTime;
	}
	public void setPlayTime(String playTime) {
		PlayTime = playTime;
	}
	public avaliableParkingSlots() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<String> getAvaliableSlots() {
		return avaliableSlots;
	}
	public void setAvaliableSlots(List<String> avaliableSlots) {
		this.avaliableSlots = avaliableSlots;
	}
	public avaliableParkingSlots(String movieTitle, String date, String playTime, List<String> avaliableSlots) {
		super();
		this.movieTitle = movieTitle;
		this.date = date;
		PlayTime = playTime;
		this.avaliableSlots = avaliableSlots;
	}
	
	
	
	

}
