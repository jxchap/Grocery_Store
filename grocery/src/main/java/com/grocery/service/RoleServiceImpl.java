package com.grocery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.domain.Role;
import com.grocery.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
