package com.dj.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR_PRICING_DETAILS")
public class PricingDetails {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "VENDOR_PRICING_DETAILS_ID")
    private int pricingDetailsId;
	
    private Double hourlyRate;
    private Double dailyRate;
    private int status;
    private Date createdOn;
    private Date modifiedOn;
    private int userLocalityId;

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

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getUserLocalityId() {
        return userLocalityId;
    }

    public void setUserLocalityId(int userLocalityId) {
        this.userLocalityId = userLocalityId;
    }

    public int getPricingDetailsId() {
        return pricingDetailsId;
    }

    public void setPricingDetailsId(int pricingDetailsId) {
        this.pricingDetailsId = pricingDetailsId;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }
    
}
