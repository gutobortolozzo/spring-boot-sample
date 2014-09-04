
package com.sample.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.entity.Person;
import com.sample.repository.PersonRepository;

@RestController
public class PersonController {
		
	@Autowired private PersonRepository repo;
	
	@RequestMapping("/people.json")
	public Iterable<Person> people() {
		return repo.findAll();
	}
	
	@RequestMapping("/person.json/{name}")
	public Iterable<Person> person(@PathVariable("name") String name) {
		return repo.find(name);
	}
	
	@RequestMapping("/person.json/delete/{name}")
	public void delete(@PathVariable("name") String name) {
		repo.find(name).forEach(person -> repo.delete(person));
	}
	
	@ExceptionHandler
	public Iterable<Person> error(){
		return Collections.emptyList();
	}
}
