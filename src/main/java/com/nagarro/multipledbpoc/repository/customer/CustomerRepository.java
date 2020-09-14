package com.nagarro.multipledbpoc.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.domain.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
