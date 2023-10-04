package com.proyecto.app.DTOs;

import java.io.Serializable;

public class UserDTO implements Serializable  {

	private static final long serialVersionUID=1L;
	
	private Long id;
	private String name;
	private String lastName;
	private int age;
	private String cellphone;
	private String city;
	private String email;
	private String role;
	private Boolean status;
	
	public UserDTO(Long id, String name, String lastName, int age, String cellphone, String role,String email,String city,Boolean status) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.cellphone = cellphone;
		this.role = role;
		this.email = email;
		this.city = city;
	}

	public UserDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
