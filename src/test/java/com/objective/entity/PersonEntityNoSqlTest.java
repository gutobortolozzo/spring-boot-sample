package com.objective.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.objective.AbstractSpringConfiguration;
import com.sample.entity.Person;
import com.sample.repository.PersonRepositoryNoSql;

public class PersonEntityNoSqlTest extends AbstractSpringConfiguration{
	
	@Autowired private PersonRepositoryNoSql repo;
	
	private Person person = new Person("John", "Carmack", new Date());
	
	@Test public void thereIsMongoInstance(){
		assertNotNull(repo.findAll());
	}
	
	@Test public void saveAndCount(){
		repo.save(person);
		assertEquals(1, repo.count(), 0);
	}
	
	@Test public void saveAndQuery() {
		repo.save(person);
		Collection<Person> people = repo.find("John");
		assertEquals(1, people.size());
	}
}
