package com.grocery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Z_ServiceUtilities {

	@Autowired
	CustomerService customerService;

	@Autowired
	EmployeeService employeeService;

	public boolean checkForExistingUsernameOrEmail(String username, String email) {

		var checkCustomerUsername = customerService.findByUsername(username);
		var checkCustomerEmail = customerService.findByEmail(email);

		var checkEmployeeUsername = employeeService.findEmployeeByUsername(username);
		var checkEmployeeEmail = employeeService.findEmployeeByEmail(email);
		
		if (checkCustomerUsername != null) return true;
		if (checkCustomerEmail != null) return true;
		if (checkEmployeeUsername != null) return true;
		if (checkEmployeeEmail != null) return true;
		

		return false;

	}

}
