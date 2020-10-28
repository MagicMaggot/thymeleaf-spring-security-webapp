package com.babooin.service;

import java.util.List;
import java.util.Optional;

import com.babooin.entity.customers.Customer;

public interface CustomerService {
	
	List<Customer> findAll();
	List<Customer> findAllByName(String name);
	Optional<Customer> findById(int id);
	void save(Customer customer);
	void deleteById(int id);
	
}
