package com.drivein.filter;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class routeValidater {
	
	public static final  List<String> openApiEndPoints=List.of(
			"/auth/register",
			"/auth/token",
			"/eureka",
			"/api-docs/",
			"/Booking/swagger-ui/",
			"/Booking/api-docs",
			"/catalog/",
			"/shedule/"
			);
	
	public Predicate<ServerHttpRequest> isSecured=
			request -> openApiEndPoints
			.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
	

}
