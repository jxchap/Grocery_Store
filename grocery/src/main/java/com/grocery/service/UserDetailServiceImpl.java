package com.grocery.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grocery.domain.Customer;
import com.grocery.domain.Employee;
import com.grocery.domain.Role;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer customer = customerService.findByUsername(username);
		
		if(customer != null) {
			
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			Set<Role> roles = customer.getRoles();
			for (Role role : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}

			return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(),
					grantedAuthorities);
			
		}else {
			
			//another if statement if employee is not found? username not found in system?
			Employee employee = employeeService.findEmployeeByUsername(username);
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			Set<Role> roles = employee.getRoles();
			for (Role role : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}

			return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(),
					grantedAuthorities);
		}
		
		

	}

}
