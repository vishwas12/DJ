package com.dj.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR_PACKAGES")
public class VendorPackages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_PACKAGE_ID")
	private Long vendorPackageId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "PACKAGE_DETAILS_ID")
	private VendorPackageDetails packageDetails;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "PRICING_DETAILS_ID")
	private PricingDetails pricingDetails;

	public Long getVendorPackageId() {
		return vendorPackageId;
	}

	public void setVendorPackageId(Long vendorPackageId) {
		this.vendorPackageId = vendorPackageId;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public VendorPackageDetails getPackageDetails() {
		return packageDetails;
	}

	public void setPackageDetails(VendorPackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}

	public PricingDetails getPricingDetails() {
		return pricingDetails;
	}

	public void setPricingDetails(PricingDetails pricingDetails) {
		this.pricingDetails = pricingDetails;
	}
	
	
	

}
