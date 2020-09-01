package com.babooin.model.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeForm {
	
	@NotNull(message = "{form.customer.message.firstName.empty}")
	@Size(min = 3, max = 20, message = "{form.customer.message.firstName.size}")
	private String firstName;
	@NotNull(message = "{form.customer.message.firstName.empty}")
	@Size(min = 3, max = 20, message = "{form.customer.message.lastName.size}")
	private String lastName;
	private String department;
	
	private int id;
	
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
