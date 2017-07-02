package com.dj.dto;

import java.util.Date;
import java.util.List;

public class SearchResults {

    private int userId;
    private String firstName;
    private String lastName;
    private int status;
    private Date createdOn;
    private String dataLocation;
    private Double hourlyRate;
    private Double dailyRate;
    private List<Date> futureBookedDates;
    
    private int numberOfVerifiedBookings;
    private List<Review> reviews;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
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

    public int getNumberOfVerifiedBookings() {
        return numberOfVerifiedBookings;
    }

    public void setNumberOfVerifiedBookings(int numberOfVerifiedBookings) {
        this.numberOfVerifiedBookings = numberOfVerifiedBookings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    public List<Date> getFutureBookedDates() {
        return futureBookedDates;
    }

    public void setFutureBookedDates(List<Date> futureBookedDates) {
        this.futureBookedDates = futureBookedDates;
    }

    @Override
    public String toString() {
        return "SearchResults [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", status=" + status + ", createdOn=" + createdOn + ", dataLocation=" + dataLocation + ", hourlyRate="
                        + hourlyRate + ", dailyRate=" + dailyRate + ", futureBookedDates=" + futureBookedDates + ", numberOfVerifiedBookings=" + numberOfVerifiedBookings + ", reviews=" + reviews + "]";
    }

    
    


}
