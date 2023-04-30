package com.drivein.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.drivein.entities.userDetails;
@Configuration
public class beanconfiguration {
	
	
	@Bean
	public userDetails getuDetails() {
		return  new userDetails() ;
	}

}
