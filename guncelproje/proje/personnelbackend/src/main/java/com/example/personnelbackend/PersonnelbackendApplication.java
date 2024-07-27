package com.example.personnelbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonnelbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelbackendApplication.class, args);
	}

}
