package com.objective.entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.objective.AbstractSpringConfiguration;
import com.sample.entity.Person;
import com.sample.repository.PersonRepositorySql;

public class PersonEntitySqlTest extends AbstractSpringConfiguration {

	@Autowired private PersonRepositorySql repo;
	
	@Test public void thereIsRepo(){
		assertNotNull(repo.findAll());
	}
	
	@Test public void queryAllPeople(){
		assertEquals(1, repo.count(), 0);
	}
	
	@Test public void deletePerson(){
		repo.deleteAll();
		assertEquals(0, repo.count(), 0);
	}
	
	@Test public void queryForPerson(){
		assertTrue(repo.find("Gierke").iterator().hasNext());
	}
	
	@Test public void queryForPersonNotInDatasource(){
		assertFalse(repo.find("Livia").iterator().hasNext());
	}
	
	@Test public void matchPersonFound(){
		repo.findAll().forEach(person -> assertEquals("Gierke", person.getName()));
	}

	@Before public void setup(){
		Person person = new Person("Gierke", "Oliver", new Date());
		repo.save(person);
	}
}
