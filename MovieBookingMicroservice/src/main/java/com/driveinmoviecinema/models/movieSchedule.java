package com.driveinmoviecinema.models;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class movieSchedule {

	
	private int movieId;
	
	private String movieTitle;
//	@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date should be in dd-MM-yyyy format")
	private String date;
	private String showTime;

	private double priceForFirstTwoRows;

	private double priceForMiddleTwoRows;

	private double priceForLastTwoRows;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
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
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public movieSchedule(int movieId, String movieTitle, String date, String showTime) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.date = date;
		this.showTime = showTime;
	}
	public movieSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getPriceForFirstTwoRows() {
		return priceForFirstTwoRows;
	}
	public void setPriceForFirstTwoRows(double priceForFirstTwoRows) {
		this.priceForFirstTwoRows = priceForFirstTwoRows;
	}
	public double getPriceForMiddleTwoRows() {
		return priceForMiddleTwoRows;
	}
	public void setPriceForMiddleTwoRows(double priceForMiddleTwoRows) {
		this.priceForMiddleTwoRows = priceForMiddleTwoRows;
	}
	public double getPriceForLastTwoRows() {
		return priceForLastTwoRows;
	}
	public void setPriceForLastTwoRows(double priceForLastTwoRows) {
		this.priceForLastTwoRows = priceForLastTwoRows;
	}
	


}
