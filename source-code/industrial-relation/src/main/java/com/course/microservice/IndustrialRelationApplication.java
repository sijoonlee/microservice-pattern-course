package com.course.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IndustrialRelationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndustrialRelationApplication.class, args);
	}

}
