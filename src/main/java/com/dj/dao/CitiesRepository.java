package com.dj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dj.dto.Cities;

public interface CitiesRepository extends JpaRepository<Cities, Long>{
	
	List<Cities> findByStateId(long stateId);

}
