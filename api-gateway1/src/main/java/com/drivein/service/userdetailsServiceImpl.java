package com.drivein.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.drivein.entities.userDetails;
import com.drivein.repository.uderDetailsRepository;


@Service
public class userdetailsServiceImpl implements userdetailsService {
	
	@Autowired
	uderDetailsRepository uDetailsRepository;
	
	
	@Bean
	public userDetails getuDetails() {
		return  new userDetails("","","") ;
	}
	

	public  userDetails ValidateUser(userDetails uDetails) {
		
		Optional<userDetails> userdetailsObj=uDetailsRepository.findById(uDetails.getUserName());
		userDetails userDetails1=userdetailsObj.orElse(null);
		
		if (userdetailsObj.isEmpty()) {
			uDetails.setRole("invalid");
			return uDetails; 
		}
		
		else {
			if(userDetails1.getPwd().equals( uDetails.getPwd())) {
				if (userDetails1.getRole().equals("admin")) {
					uDetails.setRole("admin");
					return uDetails;
				}
				else if (userDetails1.getRole().equals("user")) {
					uDetails.setRole("user");
					return uDetails;
				}
				else {
					uDetails.setRole("invalid");
					return uDetails;
				}
			}
			else {
				uDetails.setRole("wrongPwd");
				
					return uDetails;
			}
			
		}
		
	}
	
	
	
	
	
	
	

}
