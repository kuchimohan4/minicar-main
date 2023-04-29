package com.Deka.MovieSchedule.MovieScheduleService.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
class swaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") 
                                 String appDesciption, 
                                 @Value("${application-version}") 
                                 String appVersion) {
        return new OpenAPI()
                .info(new Info()
                .title("MOVIE SHEDULE")
                .version(appVersion)
                .description(appDesciption)
                .termsOfService("http://swagger.io/terms/")
                .license(new License().
                         name("Apache 2.0").
                         url("http://springdoc.org")));
    }

}