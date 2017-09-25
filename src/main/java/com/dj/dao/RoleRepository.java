package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dj.dto.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRole(String Role);

}
