package com.dj.model;

import java.io.Serializable;
import java.util.Date;

import com.dj.dto.Address;

public class VendorDto implements Serializable{

		private Long vendorId;
		private String firstName;
		private String lastName;
		private Address address;
		private String email;
		private String mobileNumber;
		private Long categoryId;
		private Integer status;
		private Date createdOn;
		private Boolean isEmailVerified =false;
		private String category;
		private Boolean equipmentsExist =false;

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

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Date getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}

		public Boolean getIsEmailVerified() {
			return isEmailVerified;
		}

		public void setIsEmailVerified(Boolean isEmailVerified) {
			this.isEmailVerified = isEmailVerified;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Boolean getEquipmentsExist() {
			return equipmentsExist;
		}

		public void setEquipmentsExist(Boolean equipmentsExist) {
			this.equipmentsExist = equipmentsExist;
		}
		
		

}
