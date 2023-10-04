package com.proyecto.app.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proyecto.app.DTOs.UserDTO;

@Entity
@Table(name = "USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String lastName;
	private String email;
	private int age;
	private String cellphone;
	private String role;
	private String city;
	private Date dateOfBirth;
	private Boolean status;
	
	public User(String name, String lastName, String email, int age, String cellphone, String role, String city) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.cellphone = cellphone;
		this.role = role;
		this.city = city;
		this.age = this.calculateAge();
		this.status = true;
	}

	public User() {
		this.status = true;
	}

	public Long getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int calculateAge() {
        LocalDate currentDate = LocalDate.now();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        LocalDate birthDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(birthDate, currentDate).getYears();
    }
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public UserDTO toDTO() {
		UserDTO userDto = new UserDTO(id,name,lastName,age,cellphone,role,email,city,status);
		return userDto;
	}
}
