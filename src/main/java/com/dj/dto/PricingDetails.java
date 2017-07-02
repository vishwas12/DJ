package com.dj.dto;

import java.util.Date;

public class PricingDetails {
    private int pricingDetailsId;
    private int userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "PricingDetails [pricingDetailsId=" + pricingDetailsId + ", userId=" + userId + ", hourlyRate=" + hourlyRate + ", dailyRate=" + dailyRate + ", status=" + status + ", createdOn=" + createdOn
                        + ", modifiedOn=" + modifiedOn + ", userLocalityId=" + userLocalityId + "]";
    }

   

    
}
