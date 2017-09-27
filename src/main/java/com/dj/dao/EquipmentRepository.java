package com.dj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dj.dto.Equipments;

public interface EquipmentRepository extends JpaRepository<Equipments, Long>{
	
	@Query("from Equipments e where e.category.categoryId = :categoryId")
	List<Equipments> findByCategoryId(@Param("categoryId") Long categoryId);

}
