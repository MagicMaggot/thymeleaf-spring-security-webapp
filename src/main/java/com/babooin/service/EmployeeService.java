package com.babooin.service;

import java.util.List;
import java.util.Optional;

import com.babooin.entity.employees.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	List<Employee> findAllByName(String name);
	Optional<Employee> findById(int id);
	void save(Employee employee);
	void deleteById(int id);

}
