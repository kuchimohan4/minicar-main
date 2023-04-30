package com.drivein.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.drivein.entities.userDetails;

import reactor.core.publisher.Mono;

@Component
public class loggerFilter implements GlobalFilter {
	

	@Autowired
	private userDetails userDetails;

	private Logger logger=LoggerFactory.getLogger(loggerFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("path of request receved -> {}",exchange.getRequest().getPath());
		exchange.getResponse().addCookie(ResponseCookie.from("role",validateuserrole()).build());
//		exchange.getRequest().mutate().header("role", validateuserrole());
		return chain.filter(exchange);
	}
	
	
	
	public String validateuserrole() {
		logger.info(userDetails.getUserName()+" trying to use service");
		if((userDetails.getRole().equals("user")  |userDetails.getRole().equals("admin"))) {
			
			System.out.println("1st");
			return userDetails.getRole();
		}
		else {
//			System.out.println(userDetails.getRole());
			return "invalid";
		}
		
		
	}
	
	
	
	
	

}
