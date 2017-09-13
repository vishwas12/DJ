package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dj.dto.UserVerification;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
	
	@Query("select u from UserVerification u where u.user.userId = :userId")
	UserVerification findByUserId(@Param("userId") Long userId);
	
}
