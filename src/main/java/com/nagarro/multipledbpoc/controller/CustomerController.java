package com.nagarro.multipledbpoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.multipledbpoc.domain.customer.Customer;
import com.nagarro.multipledbpoc.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/users")
	public List<Customer> getUsers() {
		return customerService.getUsers();
	}
	
	@PostMapping("/users")
	public Customer saveUser(@RequestBody Customer customer ) {
		return customerService.save(customer);
	}
}
