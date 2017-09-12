package com.dj.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR_DETAILS")
public class VendorDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_DETAILS_ID")
	private Long vendorDetailsId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDOR_ID")
	private Vendor vendor;
	
	
	
	public Long getVendorDetailsId() {
		return vendorDetailsId;
	}
	public void setVendorDetailsId(Long vendorDetailsId) {
		this.vendorDetailsId = vendorDetailsId;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	

}
