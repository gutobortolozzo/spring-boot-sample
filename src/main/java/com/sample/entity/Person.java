package com.sample.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="person")
public class Person {
	
	@Id
	@GeneratedValue(strategy=AUTO)
	private long id;
	
	@Column(name="name")
	final private String name;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_birth")
	private Date birthDay;

	public Person(String name, String lastName, Date birthDay) {
		this.name = name;
		this.lastName = lastName;
		this.birthDay = birthDay;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}
}
