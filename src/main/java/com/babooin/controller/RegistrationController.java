package com.babooin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.babooin.dao.security.RoleDAO;
import com.babooin.entity.security.Role;
import com.babooin.entity.security.User;
import com.babooin.entity.security.Role.USER_ROLE;
import com.babooin.model.forms.UserRegistrationForm;
import com.babooin.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/registration")
	public String registrationForm(Model m) {
		
		List<Role> roles = roleDAO.findAllByOrderById();
		UserRegistrationForm form = new UserRegistrationForm();
		form.setSelectedRole(USER_ROLE.USER.getCode());
		m.addAttribute("userRegistrationForm", form);
		m.addAttribute("roles", roles);
		
		return "user-registration-form";
	}
	
	@PostMapping("/registerUser")
	public String confirm(@ModelAttribute @Valid UserRegistrationForm userRegistrationForm, BindingResult result, Model m) {
		
		Optional<User> exists = userService.findByUsername(userRegistrationForm.getUsername());
		
		if (exists.isPresent()) {
			m.addAttribute("registrationError", "User with this name already exists");
			return "user-registration-form";
		} else if (result.hasErrors())
			return "user-registration-form";
		
		
		User newUser = new User(userRegistrationForm);
		 
		userService.save(newUser);
		m.addAttribute("userRegistered", "You can now login with a new user");
		
		return "login-page";
		
	}

}
