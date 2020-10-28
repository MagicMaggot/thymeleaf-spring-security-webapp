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

import com.babooin.entity.employees.Employee;
import com.babooin.model.forms.EmployeeForm;
import com.babooin.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute("departments")
	public String[] getDepartments() {
		return Employee.getDepartments();
	}
	
	@GetMapping("/list")
	public String getEmployeeList(Model m) {
		m.addAttribute("employees", employeeService.findAll());
		return getEmployeesList();
	}
	
	@GetMapping("/addEmployee")
	public String getAddEmployeeForm(Model m) {
		m.addAttribute("employee", new EmployeeForm());
		return getEmployeeAddForm();
	}
	
	@GetMapping("/updateEmployee")
	public String updateEmployee(@RequestParam int employeeId, Model m) {
		m.addAttribute("employee", employeeService.findById(employeeId).orElse(new Employee()));
		return getEmployeeAddForm();
	}
	
	@PostMapping("/saveEmployee")
	public String addEmployee(@ModelAttribute("employee") @Valid EmployeeForm employeeForm, BindingResult result) {
		if (result.hasErrors()) {
			return getEmployeeAddForm();
		}
		employeeService.save(new Employee(employeeForm));
		return getRedirectEmployeesList();
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam int employeeId) {
		employeeService.deleteById(employeeId);
		return getRedirectEmployeesList();
	}
	
	@GetMapping("/search")
	public String searchEmployees(@RequestParam("searching") String name, Model m) {
		if (name == null)
			m.addAttribute("employees", employeeService.findAll());
		else
			m.addAttribute("employees", employeeService.findAllByName(name));
		return getEmployeesList();
	}
	
	
	
	
	
	
	private String getRedirectEmployeesList() {
		return "redirect:/employees/list";
	}
	
	private String getEmployeeAddForm() {
		return "employees/employee-add-form";
	}
	
	private String getEmployeesList() {
		return "/employees/employees-list";
	}
	
}
