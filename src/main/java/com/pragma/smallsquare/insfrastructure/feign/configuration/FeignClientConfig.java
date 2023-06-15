package com.pragma.smallsquare.insfrastructure.feign.configuration;

import com.pragma.smallsquare.insfrastructure.security.service.JwtContextHolder;
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