package com.dj.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR_VERIFICATION")
public class VendorVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_VERIFICATION_ID")
	private Long vendorVerificationId;
	
	@Column(name = "VERIFICATION_CODE")
	private String verificationCode; 
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "VENDOR_ID")
	private Vendor vendor;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	public Long getVendorVerificationId() {
		return vendorVerificationId;
	}

	public void setVendorVerificationId(Long vendorVerificationId) {
		this.vendorVerificationId = vendorVerificationId;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
}
