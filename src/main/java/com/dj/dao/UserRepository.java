package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dj.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , CrudRepository<User, Long>{
	
	Long countByEmail(String email);
	
	@Modifying
	@Query("update User u SET u.isEmailVerified = true WHERE u.userId =:userId ")
	@Transactional
	void updateEmailVerificationStatus(@Param("userId") Long userId);

}
