package com.dj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dj.dto.Locations;

public interface LocationsRepository extends JpaRepository<Locations, Long> {
	
	List<Locations> findByPinCode(String pincode);

}
