package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dj.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , CrudRepository<User, Long>{
	
	Long countByEmail(String email);

}
