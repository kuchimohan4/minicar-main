package com.drivein.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.drivein.util.jwtServiceutil;



@Component
public class authenticationfilter extends AbstractGatewayFilterFactory<authenticationfilter.Config> {

	@Autowired
	private routeValidater routeValidater;
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private jwtServiceutil jwtServiceutil;
	
	
	public authenticationfilter() {
		super(Config.class);
	}
	
	public static class Config{}

	@Override
	public GatewayFilter apply(Config config) {
		
		return ((exchange,chain)->{
			
			if (routeValidater.isSecured.test(exchange.getRequest())) {
			
			if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				
				throw new RuntimeException("missing authentication header");
				
				
			}
			String authHaeders=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			
			if(authHaeders!=null && authHaeders.startsWith("Bearer ")) {
				
				authHaeders=authHaeders.substring(7);
				
			}
			
			try {
				
//				template.getForObject("http://SECURITY-SERVICE", String.class);
				jwtServiceutil.validateToken(authHaeders);
				
			} catch (Exception e) {
				throw new RuntimeException("invalid access to application");
			}
			
			}
			
			return chain.filter(exchange);
		});
	}
}
