package com.course.microservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	private int age;

	@Column(nullable = false, length = 100)
	@Email
	private String email;

	@JsonIgnore
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [age=" + age + ", email=" + email + ", id=" + id + ", name=" + name + "]";
	}

}
