package com.drivein.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class userDetails {
	@Id
	private String userName;
	private String pwd;
	private String role;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public userDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public userDetails(String userName, String pwd, String role) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.role = role;
	}
	
	
	
	

}
