package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dj.dto.VendorVerification;

public interface VendorVerificationRepository extends JpaRepository<VendorVerification, Long> {

	@Query("select v from VendorVerification v where v.vendor.vendorId = :vendorId")
	VendorVerification findByVendorId(@Param("vendorId") Long vendorId);

}
