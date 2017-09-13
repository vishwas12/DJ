package com.dj.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR")
public class Vendor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_ID")
	private Long vendorId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name = "CATEGORY_ID")
	private int categoryId;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@OneToMany(mappedBy ="vendor", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<VendorPackages> vendorPackages;
	
	@OneToMany(mappedBy ="vendor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<VendorDetails> vendorDetails;
	
	@OneToMany(mappedBy ="vendor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<VendorReviews> vendorReviews;
	
	@OneToMany(mappedBy ="vendor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<VendorContent> vendorContent;
	
	@OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL)
	private VendorVerification vendorVerification;
	
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<VendorPackages> getVendorPackages() {
		return vendorPackages;
	}
	public void setVendorPackages(List<VendorPackages> vendorPackages) {
		this.vendorPackages = vendorPackages;
	}
	public List<VendorDetails> getVendorDetails() {
		return vendorDetails;
	}
	public void setVendorDetails(List<VendorDetails> vendorDetails) {
		this.vendorDetails = vendorDetails;
	}
	public Set<VendorReviews> getVendorReviews() {
		return vendorReviews;
	}
	public void setVendorReviews(Set<VendorReviews> vendorReviews) {
		this.vendorReviews = vendorReviews;
	}
	public List<VendorContent> getVendorContent() {
		return vendorContent;
	}
	public void setVendorContent(List<VendorContent> vendorContent) {
		this.vendorContent = vendorContent;
	}
	public VendorVerification getVendorVerification() {
		return vendorVerification;
	}
	public void setVendorVerification(VendorVerification vendorVerification) {
		this.vendorVerification = vendorVerification;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	
}
