package com.zadatak.zadatak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ZadatakApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZadatakApplication.class, args);
	}

}
