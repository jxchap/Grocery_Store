package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Employee findByUsername(String username);
	Employee findByEmail(String email);
	
	List<Employee> findByRole(String role);

}
