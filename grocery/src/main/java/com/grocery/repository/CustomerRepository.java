package com.grocery.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.domain.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByUsername(String userName);
	public Customer findByEmail(String email);
	
	public List<Customer> findAll();
	public Customer save(Customer u);
	public void deleteCustomerByUsername(String username);
	
}
