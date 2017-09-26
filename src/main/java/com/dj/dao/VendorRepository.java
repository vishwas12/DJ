package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dj.dto.Vendor;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>, CrudRepository<Vendor, Long> {

	Long countByEmail(String email);
	
	@Modifying
	@Query("update Vendor v SET v.isEmailVerified = true WHERE v.vendorId =:vendorId ")
	@Transactional
	void updateEmailVerificationStatus(@Param("vendorId") Long vendorId);
	
	Vendor findByEmail(String email);
	
	Vendor findByVendorId(Long vendorId);

	@Query("select v from Vendor v left join fetch v.category where v.vendorId = :id")
	Vendor findVendorWithCategory(@Param("id") Long id);

}
