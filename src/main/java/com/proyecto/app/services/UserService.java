package com.proyecto.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.app.repositories.IARepositoryUser;

@Service
public class UserService {
	
	@Autowired
	private IARepositoryUser RepositoryUser;

}
