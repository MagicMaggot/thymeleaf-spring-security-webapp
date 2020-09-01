package com.babooin.model.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.babooin.entity.security.Role;
import com.babooin.entity.security.Role.USER_ROLE;
import com.babooin.validation.FieldMatch;

@FieldMatch(field = "password", fieldMatch = "matchingPassword", message = "Password fields don't match")
public class UserRegistrationForm {
	
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@NotNull(message = "{form.user.registration.message.username.empty}")
	@Size(min = 4, max = 15, message = "{form.user.registration.message.username.size}")
	private String username;
	
	@NotNull(message = "{form.user.registration.message.password.empty}")
	@Size(min = 6, max = 20, message = "{form.user.registration.message.password.size}")
	private String password;
	
	private String matchingPassword;
	
	@NotNull(message = "{form.user.registration.message.firstName.empty}")
	@Size(min = 3, max = 20, message = "{form.user.registration.message.firstName.size}")
	private String firstName;
	
	@Size(min = 3, max = 20, message = "{form.user.registration.message.lastName.size}")
	private String lastName;
	
	@NotNull(message = "{form.customer.message.email.empty}")
	@Email(message = "{form.customer.message.email.valid}")
	private String email;
	
	private int selectedRole;
	
	public BCryptPasswordEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(BCryptPasswordEncoder encoder) {
		UserRegistrationForm.encoder = encoder;
	}

	public int getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(int selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEncryptedPassword() {
		return encoder.encode(password);
	}
	
}
