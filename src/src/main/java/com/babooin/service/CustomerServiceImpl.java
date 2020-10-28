package com.babooin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babooin.dao.customers.CustomerDAO;
import com.babooin.entity.customers.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public List<Customer> findAllByName(String name) {
		return customerDAO.findAllByName(name);
	}

	@Override
	public Optional<Customer> findById(int id) {
		return customerDAO.findById(id);
	}

	@Override
	public void save(Customer customer) {
		customerDAO.save(customer);
		
	}

	@Override
	public void deleteById(int id) {
		customerDAO.deleteById(id);
		
	}
	
	

}
