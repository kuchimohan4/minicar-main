package com.drivein;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class loggerFilter implements GlobalFilter {

	private Logger logger=LoggerFactory.getLogger(loggerFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("path of request receved -> {}",exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
