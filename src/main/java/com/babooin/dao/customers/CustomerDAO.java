package com.babooin.dao.customers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.babooin.entity.customers.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
	@Query("from Customer where firstName like %:name% or lastName like %:name%")
	List<Customer> findAllByName(@Param(value = "name") String name);
	List<Customer> findAllByLastNameOrFirstName(String fName, String lName);

}
