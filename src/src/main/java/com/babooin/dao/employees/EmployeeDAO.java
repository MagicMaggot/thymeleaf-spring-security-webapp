package com.babooin.dao.employees;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babooin.entity.employees.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
	
	@Query("from Employee where firstName like %:name% or lastName like %:name%")
	List<Employee> findAllByName(@Param(value = "name") String name);
	List<Employee> findAllByOrderByLastNameDesc();
	List<Employee> findAllByOrderByIdDesc();

}
