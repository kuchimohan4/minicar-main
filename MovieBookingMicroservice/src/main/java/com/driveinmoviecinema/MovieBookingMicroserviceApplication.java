package com.driveinmoviecinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableFeignClients
//@EnableSwagger2
public class MovieBookingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingMicroserviceApplication.class, args);
	}

}
