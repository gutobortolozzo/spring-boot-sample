package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sample.controller.PersonController;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class Startup {
	
	public static void main(String[] args) {
		Class<?>[] context = {Startup.class, PersonController.class};
		SpringApplication.run(context, args);
	}
}
