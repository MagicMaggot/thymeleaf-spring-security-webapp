package com.babooin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.babooin.dao.security.RoleDAO;
import com.babooin.dao.security.UserDAO;
import com.babooin.entity.security.Role;
import com.babooin.entity.security.User;
import com.babooin.entity.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired 
	private RoleDAO roleDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userDAO.findByUsername(username);
		
		User myUser = user.get();
		
		myUser.getRoles().size();

//		System.out.println(user.get());
//		
//		List<Role> roles = roleDAO.findAll();
//		
//		roles.forEach(System.out::println);
		
		return new UserDetailsImpl(myUser);
	}

	@Override
	public void save(User user) {
		
		List<Role> roles = new ArrayList<>();
		user.getRoleCodes().forEach(e -> roles.add(roleDAO.findById(e).get()));
		user.setRoles(roles);
		
		userDAO.save(user);

	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

}
