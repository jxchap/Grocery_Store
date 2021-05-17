package com.grocery.service;

import java.util.List;

import com.grocery.domain.Customer;

public interface CustomerService {

	public Customer findByUsername(String userName);
	public Customer findByEmail(String email);
	
	public List<Customer> findAll();
	public Customer save(Customer u);
	public void deleteCustomerByUsername(String username);
	
}
