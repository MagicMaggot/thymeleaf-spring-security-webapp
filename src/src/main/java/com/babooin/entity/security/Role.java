package com.babooin.entity.security;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "roles")
public class Role {
	
	public enum USER_ROLE {
	
		USER(1) {
			public String toString() {
				String name = USER.name();
				return name.charAt(0) + name.substring(1).toLowerCase();
			}
		}, 
		
		MANAGER(2) {
			public String toString() {
				String name = MANAGER.name();
				return name.charAt(0) + name.substring(1).toLowerCase();
			}
		}, 
		
		ADMIN(3) {
			public String toString() {
				String name = ADMIN.name();
				return name.charAt(0) + name.substring(1).toLowerCase();
			}
		};
		private int roleCode;
		
		private USER_ROLE(int code) {
			roleCode = code;
		}

		public int getCode() {
			return roleCode;
		}
			
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String role;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
				joinColumns = @JoinColumn(name="role_id"),
				inverseJoinColumns = @JoinColumn(name="user_id")
				)	
	private List<User> users;
	
	@Transient
	private String frinedlyName = "";

	public String getFrinedlyName() {
		return frinedlyName;
	}

	public void setFrinedlyName(String frinedlyName) {
		this.frinedlyName = frinedlyName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getFriendlyName() {
		return role.charAt(5) + role.substring(6).toLowerCase();
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", users=" + usersList() + "]";
	}
	
	public Set<String> usersList() {
		return users.stream()
				.map(e -> e.getUsername()).collect(Collectors.toSet());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		Role comparing = (Role) obj;
		
		return Objects.equals(id, role);
	}
	
	public void print() {
		System.out.println("ID: " + id + ", ROLE: " + role);
	}
	
}
