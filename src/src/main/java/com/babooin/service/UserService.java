package com.babooin.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.babooin.entity.security.User;

public interface UserService extends UserDetailsService {
	
	void save(User user);
	Optional<User> findByUsername(String username);
}
