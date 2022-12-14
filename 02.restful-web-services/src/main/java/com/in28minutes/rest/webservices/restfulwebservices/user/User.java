package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;


//@JsonIgnoreProperties(value = {"id", "birthDate"})
public class User {
	
	private Integer id;
	
	@Size(min=2, message = "Name should have atleast 2 characters")
	private String name;

	//@JsonIgnore - can use if we want to hide in JSON
	@Past(message = "Birth Date should be in the past")
	private LocalDate birthDate;

	public User() {
	}

	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}


