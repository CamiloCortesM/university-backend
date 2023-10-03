package com.proyecto.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.app.DTOs.UserDTO;
import com.proyecto.app.entities.User;
import com.proyecto.app.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
	
	@PostMapping
	public UserDTO saveArtist(@RequestBody User user) {
	    return userService.createUser(user);
	}
	
	@GetMapping(path = "{userId}")
	public UserDTO getUserById(@PathVariable("userId") Long userId) {
		return userService.getUserById(userId);
	}
	
	@DeleteMapping(path = "{userId}")
	public UserDTO deleteUserById(@PathVariable("userId") Long userId) {
		return userService.deleteUserById(userId);
	}
	

}
