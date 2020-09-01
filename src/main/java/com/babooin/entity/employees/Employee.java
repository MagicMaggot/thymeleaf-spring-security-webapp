package com.babooin.entity.employees;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.babooin.model.forms.EmployeeForm;

@Entity
@Table(name = "employees")
public class Employee {
	
	private static final String[] departments = {"SALES", "TECH", "SUPPORT", "FINANCE"};
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_id_generator")
	@SequenceGenerator(name = "employees_id_generator", initialValue = 1, allocationSize = 1)
	private int id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	private String department;
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(EmployeeForm form) {
		this.firstName = form.getFirstName();
		this.lastName = form.getLastName();
		this.department = form.getDepartment();
		this.id = form.getId();
	}

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

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

	public static String[] getDepartments() {
		return departments;
	}
	
	
	
	

}
