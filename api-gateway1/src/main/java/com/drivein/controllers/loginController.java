package com.drivein.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivein.apigatewayConfiguration;
import com.drivein.entities.userDetails;
import com.drivein.service.userdetailsService;

@RestController
@RequestMapping("/login")
public class loginController {
	
	@Autowired
	private userDetails userDetails;
	
	@Autowired
	private userdetailsService udetailsService;
	
	
	@PostMapping
	public String validate(@RequestBody userDetails uDetails) {
	
		String role=udetailsService.ValidateUser(uDetails).getRole();
		if(role.equals("user") |role.equals("admin")) {
			System.out.println("hi");
			userDetails.setUserName(uDetails.getUserName());
			userDetails.setPwd(uDetails.getPwd());
			userDetails.setRole(role);
		}
		System.out.println(userDetails.getRole());
		System.out.println("sot");
		return role;
		
	}
	

}
