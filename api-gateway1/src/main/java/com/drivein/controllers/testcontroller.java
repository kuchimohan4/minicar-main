package com.drivein.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivein.entities.userDetails;
@RestController
public class testcontroller {
	
	@Autowired
	private userDetails userDetails;
	
	@GetMapping("/gets")
	public userDetails rDetails() {
		
		
		return userDetails;
		
	}
	
	
	

}
