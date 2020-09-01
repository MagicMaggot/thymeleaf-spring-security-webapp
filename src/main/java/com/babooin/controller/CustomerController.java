package com.babooin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.babooin.entity.customers.Customer;
import com.babooin.model.forms.CustomerForm;
import com.babooin.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, trimmerEditor);
	}
	
	@GetMapping("/list")
	public String getCustomersList(Model m) {
		m.addAttribute("customers", customerService.findAll());
		return "customers/customers-list";
	}
	
	@GetMapping("/addCustomer")
	public String addCustomerForm(Model m) {
		m.addAttribute("customer", new CustomerForm());
		return "customers/customer-add-form";
	}
	
	@GetMapping("/updateCustomer")
	public String addCustomerForm(@RequestParam("customerId") int id, Model m) {
		
		m.addAttribute("customer", customerService.findById(id).orElseGet( () -> new Customer() ));
		return "customers/customer-add-form";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") CustomerForm customerForm, BindingResult result) {
		if (result.hasErrors()) {
			return "customers/customer-add-form";
		}
		Customer customer = new Customer(customerForm);
		customerService.save(customer);
		return "redirect:/customers/list";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam int customerId) {
		customerService.deleteById(customerId);
		return "redirect:/customers/list";
	}
	
	@GetMapping("/search")
	public String seachCustomers(@RequestParam String searching, Model m) {
		if (searching == null)
			m.addAttribute("customers", customerService.findAll());
		else 
			m.addAttribute("customers", customerService.findAllByName(searching));
		return "customers/customers-list";
	}

}
