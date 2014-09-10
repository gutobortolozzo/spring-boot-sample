
package com.sample.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.entity.Person;
import com.sample.repository.PersonRepositoryNoSql;
import com.sample.repository.PersonRepositorySql;

@RestController
public class PersonController {
		
	@Autowired private PersonRepositorySql repoSql;
	@Autowired private PersonRepositoryNoSql repoNoSql;
	
	@RequestMapping("/people.json")
	public Iterable<Person> people() {
		Collection<Person> all = repoSql.findAll();
		all.addAll(repoNoSql.findAll());
		return all;
	}
	
	@RequestMapping("/person.json/{name}")
	public Iterable<Person> person(@PathVariable("name") String name) {
		Collection<Person> collection = repoNoSql.find(name);
		collection.addAll(repoSql.find(name));
		return collection;
	}
	
	@RequestMapping("/person.json/delete/{name}")
	public void delete(@PathVariable("name") String name) {
		repoSql.find(name).forEach(person -> repoSql.delete(person));
	}
	
	@ExceptionHandler
	public Iterable<Person> error(){
		return Collections.emptyList();
	}
}
