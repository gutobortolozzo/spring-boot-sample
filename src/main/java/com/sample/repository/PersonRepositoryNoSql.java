package com.sample.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.Repository;

import com.sample.entity.Person;

public class PersonRepositoryNoSql implements Repository<Person, Integer>{
	
	@Autowired private MongoOperations mongo;
	
	public Collection<Person> findAll(){
		return mongo.findAll(Person.class);
	}
	
	public void save(Person person){
		mongo.save(person);
	}
	
	public Integer count(){
		// stop this madness on production environment
		return mongo.findAll(Person.class).size();
	}
	
	public Collection<Person> find(String name){
		Query criteria = new Query().addCriteria(Criteria.where("name").is(name));
		return mongo.find(criteria, Person.class);
	}
	
	public void truncate(String collection){
		mongo.dropCollection(collection);
	}
	
	public Set<String> collections(){
		return mongo.getCollectionNames();
	}
}
