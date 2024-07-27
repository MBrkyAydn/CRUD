package com.example.notificationbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NotificationbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationbackendApplication.class, args);
	}

}
