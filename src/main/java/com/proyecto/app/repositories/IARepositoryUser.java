package com.proyecto.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.app.entities.User;

@Repository
public interface IARepositoryUser extends JpaRepository<User, Long> {

	boolean existsByEmail(String emailToCheck);

	@Override
	Optional<User> findById(Long id);

}
