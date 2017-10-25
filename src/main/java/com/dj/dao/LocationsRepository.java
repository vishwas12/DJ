package com.dj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dj.dto.Locations;

public interface LocationsRepository extends JpaRepository<Locations, Long> {
	
	@Query(value="select l.* from Locations l where l.Pincode =:pincode limit 1",nativeQuery=true)
	List<Locations> findByPinCode(@Param("pincode") String pincode);

}
