package com.proyecto.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.entities.User;

@Repository
public interface IARepositoryUser extends JpaRepository<User, Long> {

	boolean existsByEmail(String emailToCheck);

}
