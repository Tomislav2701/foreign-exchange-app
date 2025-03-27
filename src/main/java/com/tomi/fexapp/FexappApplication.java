package com.tomi.fexapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FexappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FexappApplication.class, args);
	}

}
