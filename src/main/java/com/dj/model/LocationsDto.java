package com.dj.model;

import java.util.Set;

public class LocationsDto {
	
	private String state;
	private String district;
	private Set<String> localities;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Set<String> getLocalities() {
		return localities;
	}
	public void setLocalities(Set<String> localities) {
		this.localities = localities;
	}
	
	

}
