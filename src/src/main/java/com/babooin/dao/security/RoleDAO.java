package com.babooin.dao.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babooin.entity.security.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {
	
	List<Role> findAllByOrderById();

}
