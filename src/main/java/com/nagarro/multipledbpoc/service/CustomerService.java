package com.nagarro.multipledbpoc.service;

import java.util.List;

import com.nagarro.multipledbpoc.domain.customer.Customer;

public interface CustomerService {

	List<Customer> getUsers();

	Customer save(Customer customer);

}