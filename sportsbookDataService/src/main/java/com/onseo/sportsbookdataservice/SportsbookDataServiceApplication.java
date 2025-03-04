package com.onseo.sportsbookdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SportsbookDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsbookDataServiceApplication.class, args);
	}

}
