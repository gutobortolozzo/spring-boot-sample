package com.sample.repository;

import java.math.BigInteger;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.Repository;

import com.sample.entity.Person;

public class PersonRepositorySql implements Repository<Person, Integer>{
	
	@PersistenceContext private EntityManager em;
	
	public void save(Person person){
		em.persist(person);
	}
	
	public Collection<Person> findAll(){
		return em.createQuery("from person", Person.class).getResultList();
	}
	
	public Collection<Person> find(String name){
		final String query = "select p from person p where p.name=:person_name";
		return em.createQuery(query, Person.class).setParameter("person_name", name).getResultList();
	}
	
	public void delete(Person person){
		em.remove(person);
	}
	
	public void deleteAll(){
		em.createQuery("delete from person").executeUpdate();
	}
	
	public Integer count(){
		return ((BigInteger) em.createNativeQuery("select count(*) from person").getSingleResult()).intValue();
	}
}
