package com.babooin.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.babooin.dao.security.RoleDAO;
import com.babooin.dao.security.UserDAO;
import com.babooin.entity.security.Role;
import com.babooin.entity.security.Role.USER_ROLE;
import com.babooin.entity.security.User;
import com.babooin.model.forms.UserRegistrationForm;
import com.babooin.service.UserService;

@Controller
public class MainController {
	
	
	
	@GetMapping("/")
	public String getLoginPage() {
		return "home-page";
	}
	
	

}
