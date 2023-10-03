package com.proyecto.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.app.entities.User;

public interface IARepositoryUser extends JpaRepository<User, Long> {

}
