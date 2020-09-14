package com.nagarro.multipledbpoc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.multipledbpoc.domain.customer.Customer;
import com.nagarro.multipledbpoc.repository.customer.CustomerRepository;
import com.nagarro.multipledbpoc.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService  {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getUsers() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
}
