package com.babooin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/userLogin")
	public String getLoginPage() {
		return "login-page";
	}
	
	@GetMapping("/userLogout")
	public String logoutUser(Model m) {
		m.addAttribute("userLogout", true);
		return "login-page";
	}
	
}
