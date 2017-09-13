package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dj.dto.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser,Long>, CrudRepository<AuthUser, Long>{

}
