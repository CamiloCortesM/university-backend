package com.proyecto.app.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	public List<UserDTO> listUsers() {
		//TODO: valid with JWT only admin
    	List<User> users = RepositoryUser.findAll();
    	
    	List<UserDTO> usersDTOs = users.stream()
                .map(User::toDTO) 
                .collect(Collectors.toList());
    	return usersDTOs;
    }
	
	public UserDTO createUser(User user) {
		//TODO: validate with JWT only admin
		int age = calculateAge(user.getDateOfBirth());
        validateUser(user, age);
        
		String generatedEmail = generateUniqueEmail(user.getName(), user.getLastName());
        user.setEmail(generatedEmail);
        user.setAge(age);
        
		RepositoryUser.save(user);
		//TODO: save in subjects backend
		
		
		return user.toDTO();
	}
	
	public UserDTO getUserById (Long UserId) {
		//TODO: validate with JWT
		return RepositoryUser.getById(UserId).toDTO();
	}
	
	public UserDTO deleteUserById(Long userId) {
		 // TODO: Validar con JWT solo para administradores
	    Optional<User> userOptional = RepositoryUser.findById(userId);

	    if (userOptional.isPresent()) {
	        User userToDeactivate = userOptional.get();
	        userToDeactivate.setStatus(false); 
	        RepositoryUser.save(userToDeactivate);
	        //TODO: Delete user of subject server
	        return userToDeactivate.toDTO();
	    } else {
	        throw new IllegalStateException("User with id " + userId + " does not exist");
	    }
	}
	
	public UserDTO updateUser(Long userId, User user) {
		// TODO: validate with JWT
	    User userDb = RepositoryUser.findById(userId).orElseThrow(() -> new IllegalStateException(
	            "User with this id not exists"
	    ));

	    if (user.getName() != null && !user.getName().isEmpty()) {
	        userDb.setName(user.getName());
	    }

	    if (user.getLastName() != null && !user.getLastName().isEmpty()) {
	        userDb.setLastName(user.getLastName());
	    }

	    if (user.getCellphone() != null && !user.getCellphone().isEmpty()) {
	        userDb.setCellphone(user.getCellphone());
	    }

	    if (user.getCity() != null && !user.getCity().isEmpty()) {
	        userDb.setCity(user.getCity());
	    }

	    RepositoryUser.save(userDb);
	    return userDb.toDTO();
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
