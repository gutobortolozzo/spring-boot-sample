package com.sample.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sample.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	
	@Query("SELECT p FROM person p WHERE LOWER(p.name) = LOWER(:name)")
    public Iterable<Person> find(@Param("name") String name);
}
