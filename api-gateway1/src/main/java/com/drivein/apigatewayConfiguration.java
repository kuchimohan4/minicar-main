package com.drivein;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class apigatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayrouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("myheader", "my uri"))
						.uri("http://httpbin.org:80")
						)
				.route(p -> p.path("/movie/**")
						.uri("lb://MOVIE-CATALOG-SERVICE"))
				.route(p -> p.path("/catalog/**")
						.uri("lb://MOVIE-CATALOG-SERVICE"))
				.route(p -> p.path("/Shows/**")
						.uri("lb://MOVIE-SCHEDULE-SERVICE"))
				.route(p -> p.path("/shedule/**")
						.uri("lb://MOVIE-SCHEDULE-SERVICE"))
				.route(p -> p.path("/Booking/**")
						.uri("lb://BOOKING-SERVICE"))
				.route(p -> p.path("/v3/**")
						.uri("lb://BOOKING-SERVICE"))
				.build();
	
		
	}

}
