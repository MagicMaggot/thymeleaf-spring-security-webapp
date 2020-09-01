package com.babooin.entity.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.babooin.entity.security.Role.USER_ROLE;
import com.babooin.model.forms.UserRegistrationForm;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_sequence")
	@SequenceGenerator(name = "users_id_sequence", allocationSize = 1, initialValue = 1)
	private int id;
	
	private String username;
	
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id") )
	private List<Role> roles = new ArrayList<>();
	
	@Transient
	Set<Integer> roleCodes = new HashSet<>();
	
	public User() {
	}
	
	public User(UserRegistrationForm form) {
		username = form.getUsername();
		password = form.getEncryptedPassword();
		firstName = form.getFirstName();
		lastName = form.getLastName();
		email = form.getEmail();
		roleCodes.add(USER_ROLE.USER.getCode());
		roleCodes.add(form.getSelectedRole());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Integer> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(Set<Integer> roleCodes) {
		this.roleCodes = roleCodes;
	}

	@Override
	public String toString() {
		
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", roles=" + getRoleNames() + "]";
	}
	
	public Set<String> getRoleNames() {
		return roles.stream()
				.map(Role::getRole).collect(Collectors.toSet());
	}
	
	
}
