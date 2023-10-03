package com.proyecto.app.DTOs;

import java.io.Serializable;

public class UserDTO implements Serializable  {

	private static final long serialVersionUID=1L;
	
	private Long id;
	private String name;
	private String lastName;
	private int age;
	private String cellphone;
	private String role;
	
	public UserDTO(Long id, String name, String lastName, int age, String cellphone, String role) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.cellphone = cellphone;
		this.role = role;
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
}
