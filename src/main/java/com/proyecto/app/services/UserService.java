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
    	List<User> users = RepositoryUser.findAll();

    	List<UserDTO> usersDTOs = users.stream()
                .map(User::toDTO)
                .collect(Collectors.toList());
    	return usersDTOs;
    }

	public UserDTO createUser(User user) {
		int age = calculateAge(user.getDateOfBirth());
        validateUser(user, age);

		String generatedEmail = generateUniqueEmail(user.getName(), user.getLastName());
        user.setEmail(generatedEmail);
        user.setAge(age);
        user.setStatus(true);

		RepositoryUser.save(user);
		//TODO: save in subjects backend


		return user.toDTO();
	}

	public UserDTO getUserById(Long id) {
        Optional<User> userOptional = RepositoryUser.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.toDTO();
        }
        return null;
 }

	public UserDTO deleteUserById(Long userId) {
	    Optional<User> userOptional = RepositoryUser.findById(userId);
	    if (userOptional.isPresent()) {
	        User userToDeactivate = userOptional.get();
	        userToDeactivate.setStatus(false);
	        RepositoryUser.save(userToDeactivate);
	        return userToDeactivate.toDTO();
	    } else {
	        throw new IllegalStateException("User with id " + userId + " does not exist");
	    }
	}

	public UserDTO updateUser(Long userId, User user) {
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

	private void validateUser(User user, int age) {
	    validateName(user.getName(), user.getLastName());
	    validateRole(user.getRole());
	    validateNotNull(user.getCellphone(), "Cellphone is required");
	    validateNotNull(user.getCity(), "City is required");
	    validateCity(user.getCity());
	    validatePhoneNumber(user.getCellphone());
	    validateAge(age);
	}

	private void validateName(String firstName, String lastName) {
	    if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
	        throw new IllegalArgumentException("First and last name are required fields.");
	    }
	}

	private void validateRole(String role) {
	    if (role == null || !VALID_ROLES.contains(role.toLowerCase())) {
	        throw new IllegalArgumentException("Role must be 'student' or 'admin'.");
	    }
	}

	private void validateNotNull(Object field, String errorMessage) {
	    if (field == null) {
	        throw new IllegalArgumentException(errorMessage);
	    }
	}

	private void validateAge(int age) {
	    if (age < 16) {
	        throw new IllegalArgumentException("The user must be at least 16 years old.");
	    }
	}

	private void validateCity(String city) {
	    if (!city.matches("^[a-zA-Z\\s]+$")) {
	        throw new IllegalArgumentException("City can only contain letters and spaces.");
	    }
	}

	private void validatePhoneNumber(String phoneNumber) {
	    if (!phoneNumber.matches("^[0-9]{10,}$")) {
	        throw new IllegalArgumentException("Phone number must contain at least 10 digits and only digits.");
	    }
	}




}
