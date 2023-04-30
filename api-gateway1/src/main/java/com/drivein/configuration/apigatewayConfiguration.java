package com.drivein.configuration;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.drivein.entities.userDetails;
import com.drivein.service.userdetailsService;

@Configuration
public class apigatewayConfiguration {
	
	@Autowired
	private userdetailsService userdetailsService;
	
	
	
	
	
	@Bean
	public RouteLocator gatewayrouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
//				.route(p -> p.path("/get")
//						.filters(f -> f.addRequestHeader("role","hielo"))
//						.uri("http://httpbin.org:80")
//						)
				.route(p -> p.path("/movie/**")
//						.filters(f -> f.addRequestHeader("rol",validateuserrole() ))
						.uri("lb://MOVIE-CATALOG-SERVICE"))
				.route(p -> p.path("/catalog/**")
						.uri("lb://MOVIE-CATALOG-SERVICE"))
				.route(p -> p.path("/Shows/**")
						.uri("lb://MOVIE-SCHEDULE-SERVICE"))
				.route(p -> p.path("/shedule/**")
						.uri("lb://MOVIE-SCHEDULE-SERVICE"))
				.route(p -> p.path("/Booking/**")
						.uri("lb://BOOKING-SERVICE"))
				.build();
	}
	
	
	
		
//	public String validate
	
	

}
