package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dj.dto.UserVerification;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
	
}
