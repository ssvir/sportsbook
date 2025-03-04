package com.onseo.sportsbookretrievingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class SportsbookRetrievingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsbookRetrievingServiceApplication.class, args);
	}
	@Bean
	public HealthIndicator healthIndicator() {
		return () -> Health.up().withDetail("message", "Service is healthy").build();
	}
}
