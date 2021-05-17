package com.grocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.domain.Customer;
import com.grocery.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer findByUsername(String userName) {
		return customerRepository.findByUsername(userName);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer u) {
		return customerRepository.save(u);
	}

	@Override
	public void deleteCustomerByUsername(String username) {
		customerRepository.deleteCustomerByUsername(username);

	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
}
