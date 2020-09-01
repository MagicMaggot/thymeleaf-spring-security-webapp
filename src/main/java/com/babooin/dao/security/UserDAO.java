package com.babooin.dao.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babooin.entity.security.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}
