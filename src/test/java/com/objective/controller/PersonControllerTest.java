package com.objective.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.objective.AbstractSpringConfiguration;
import com.sample.controller.PersonController;
import com.sample.entity.Person;
import com.sample.repository.PersonRepository;

public class PersonControllerTest extends AbstractSpringConfiguration {
	
	@Autowired private PersonRepository repo;
	@Autowired private PersonController subject;
	
	@Before public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(subject).build();
		Person person = new Person("Josh", "Kris", new Date());
		repo.save(person);
	}
	
	@Test public void requestAllPeople() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/people.json")
						.accept(APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(content().string(containsString("Josh")));
	}
	
	@Test public void requestPersonNotInDatasource() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/person.json/{name}", "Douglas")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}
	
	@Test public void requestPersonInDatasource() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/person.json/{name}", "Josh")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Josh")));
	}
	
	@Test public void requestDeletePersonInDatasource() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/person.json/delete/{name}", "Josh")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/people.json")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[]")));
	}
}
