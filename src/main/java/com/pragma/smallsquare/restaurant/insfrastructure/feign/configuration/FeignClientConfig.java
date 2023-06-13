package com.pragma.smallsquare.restaurant.insfrastructure.feign.configuration;

import com.pragma.smallsquare.restaurant.insfrastructure.security.service.JwtContextHolder;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // Configure log level for Feign
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " +
                JwtContextHolder.getJwtToken());
    }
}