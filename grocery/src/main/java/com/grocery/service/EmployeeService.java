package com.grocery.service;

import java.util.List;

import com.grocery.domain.Employee;

public interface EmployeeService {
	
	public Employee findEmployeeByUsername(String username);
	public Employee findEmployeeByEmail(String email);
	
	public List<Employee> findEmployeesByRole(String username);
	public Employee save(Employee employee);

}
