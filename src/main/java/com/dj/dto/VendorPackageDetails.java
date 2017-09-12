package com.dj.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR_PACKAGE_DETAILS")
public class VendorPackageDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_PACKAGE_DETAILS_ID")
	private Long packageDetailsId;
	
	public Long getPackageDetailsId() {
		return packageDetailsId;
	}
	public void setPackageDetailsId(Long packageDetailsId) {
		this.packageDetailsId = packageDetailsId;
	}
	
	
}
