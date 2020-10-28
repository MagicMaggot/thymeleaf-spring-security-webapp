package com.babooin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.babooin.dao.security.UserDAO;
import com.babooin.entity.security.User;
import com.babooin.entity.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userDAO.findByUsername(username);
		User myUser = user.get();
		myUser.getRoles().size();
		
		return new UserDetailsImpl(myUser);
	}

	@Override
	public void save(User user) {
		userDAO.save(user);

	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

}
