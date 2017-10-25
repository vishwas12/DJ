package com.dj.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="States")
public class States {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id")
	private Long stateId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "IS_ACTIVE")
	private Boolean IS_ACTIVE;
	
	/*@OneToMany(mappedBy ="state",fetch = FetchType.LAZY)
	private Set<Locations> locations = new HashSet<>(0);*/

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIS_ACTIVE() {
		return IS_ACTIVE;
	}

	public void setIS_ACTIVE(Boolean iS_ACTIVE) {
		IS_ACTIVE = iS_ACTIVE;
	}

	/*public Set<Locations> getLocations() {
		return locations;
	}

	public void setLocations(Set<Locations> locations) {
		this.locations = locations;
	}*/
	
}
