package com.dj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dj.dto.States;

public interface StatesRepository extends JpaRepository<States, Long> {
	
	/*List<States> findAll();*/
	
}
