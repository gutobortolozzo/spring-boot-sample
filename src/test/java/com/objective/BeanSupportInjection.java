package com.objective;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.controller.PersonController;

@Configuration
public class BeanSupportInjection {

	@Bean public PersonController personController(){
		return new PersonController();
	}
}
