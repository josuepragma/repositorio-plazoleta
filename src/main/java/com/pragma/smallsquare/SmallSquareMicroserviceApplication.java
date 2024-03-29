package com.pragma.smallsquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SmallSquareMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmallSquareMicroserviceApplication.class, args);
	}

}
