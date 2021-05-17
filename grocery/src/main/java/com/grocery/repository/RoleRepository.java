package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.domain.Role;



public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Role findByRoleName(String roleName);

}
