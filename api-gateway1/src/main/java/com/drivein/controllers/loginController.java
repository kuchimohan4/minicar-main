package com.drivein.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;

import com.drivein.entities.userDetails;
import com.drivein.service.userdetailsService;

@RestController
public class loginController {
	
	@Autowired
	private userDetails userDetails;
	
	@Autowired
	private userdetailsService udetailsService;
	
	
	@PostMapping("/login")
	public String validate(@RequestBody userDetails uDetails) {
	
		String role=udetailsService.ValidateUser(uDetails).getRole();
		if(role.equals("user") |role.equals("admin")) {
//			System.out.println("hi");
			userDetails.setUserName(uDetails.getUserName());
			userDetails.setPwd(uDetails.getPwd());
			userDetails.setRole(role);
		}
//		System.out.println(userDetails.getRole());
//		System.out.println("sot");
		
		return role;
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
	
		String role=userDetails.getRole();
		if(role.equals("user") |role.equals("admin")) {
			
			userDetails.setUserName("");
			userDetails.setPwd("");
			userDetails.setRole("");
			
			
			return new ResponseEntity<>("logout sucessful",HttpStatus.ACCEPTED) ;
			
		}
		else {
			
			RedirectView  redirectView=new RedirectView("/login");
			
			return new ResponseEntity<>(redirectView, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
	
	
	

}
