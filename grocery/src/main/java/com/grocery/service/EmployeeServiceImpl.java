package com.grocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.domain.Employee;
import com.grocery.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee findEmployeeByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}

	@Override
	public List<Employee> findEmployeesByRole(String username) {
		return employeeRepository.findByRole(username);
	}

	@Override
	public Employee findEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

}
