package com.babooin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babooin.dao.employees.EmployeeDAO;
import com.babooin.entity.employees.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	public List<Employee> findAllByName(String name) {
		return employeeDAO.findAllByName(name);
	}

	@Override
	public Optional<Employee> findById(int id) {
		return employeeDAO.findById(id);
	}

	@Override
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	public void deleteById(int id) {
		employeeDAO.deleteById(id);
	}
	
	

}
