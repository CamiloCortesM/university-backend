package com.proyecto.app.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.app.DTOs.UserDTO;
import com.proyecto.app.entities.User;
import com.proyecto.app.repositories.IARepositoryUser;

@Service
public class UserService {
	
	private static final Set<String> VALID_ROLES = new HashSet<>(Arrays.asList("student", "admin"));
	
	@Autowired
	private IARepositoryUser RepositoryUser;
	
	public UserDTO createUser(User user) {
		int age = calculateAge(user.getDateOfBirth());
        validateUser(user, age);
        
		String generatedEmail = generateUniqueEmail(user.getName(), user.getLastName());
        user.setEmail(generatedEmail);
        user.setAge(age);
        
		RepositoryUser.save(user);
		//TODO: save in subjects backend
		
		
		return user.toDTO();
	}
	
	private int calculateAge(Date dateOfBirth) {
		 if (dateOfBirth == null) {
		     throw new IllegalArgumentException("Date of birth is required.");
		 }
		 
		 try {
		     LocalDate currentDate = LocalDate.now();
		     LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		     return Period.between(birthDate, currentDate).getYears();
		 } catch (Exception e) {
		     throw new IllegalArgumentException("Invalid date format for date of birth.");
		 }
    }
	
	private String generateUniqueEmail(String name, String lastName) {
        String baseEmail = name.toLowerCase() + "." + lastName.toLowerCase();
        String generatedEmail = baseEmail + "@uptc.edu.co";

        String emailToCheck = generatedEmail;
        int counter = 1;
        while (RepositoryUser.existsByEmail(emailToCheck)) {
            emailToCheck = baseEmail + counter + "@uptc.edu.co";
            counter++;
        }

        return emailToCheck;
    }
	
	private void validateUser(User user,int age) {
        if (user.getName() == null || user.getName().isEmpty() || user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("First and last name are required fields.");
        }
        String role = user.getRole();
        if (role == null || !VALID_ROLES.contains(role.toLowerCase())) {
            throw new IllegalArgumentException("Role must be 'student' or 'admin'.");
        }
       
        if (age < 16) {
            throw new IllegalArgumentException("The user must be at least 16 years old.");
        }

    }
	
	

}
