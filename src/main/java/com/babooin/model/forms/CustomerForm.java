package com.babooin.model.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerForm {
	
	@Min(value = 0)
	private int id;
	
	@NotBlank(message = "{form.customer.message.firstName.empty}")
	@Size(min = 3, max = 20, message = "{form.customer.message.firstName.size}")
	private String firstName;
	
	@Size(min = 3, max = 20, message = "{form.customer.message.lastName.size}")
	private String lastName;
	
	@NotNull(message = "{form.customer.message.email.empty}")
	@Email(message = "{form.customer.message.email.valid}")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
