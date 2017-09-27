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
@Table(name ="Locations")
public class Locations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long locationId;
	
	@Column(name = "District")
	private String district;
	
	@Column(name = "Sub_District")
	private String subDistrict;
	
	@Column(name = "Village")
	private String village;
	
	@Column(name = "Locality1")
	private String firstLocality;
	
	@Column(name = "Locality2")
	private String secondLocality;
	
	@Column(name = "Locality3")
	private String thirdLocality;
	
	@Column(name = "Office")
	private String office;
	
	@Column(name = "Pincode")
	private String pinCode;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "ID_STATE")
	private States state;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getFirstLocality() {
		return firstLocality;
	}

	public void setFirstLocality(String firstLocality) {
		this.firstLocality = firstLocality;
	}

	public String getSecondLocality() {
		return secondLocality;
	}

	public void setSecondLocality(String secondLocality) {
		this.secondLocality = secondLocality;
	}

	public String getThirdLocality() {
		return thirdLocality;
	}

	public void setThirdLocality(String thirdLocality) {
		this.thirdLocality = thirdLocality;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}
	   
	
	  


}
