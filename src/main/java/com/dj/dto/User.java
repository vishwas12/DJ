package com.dj.dto;

import java.util.Date;
import java.util.List;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String userAddress;
	private String email;
	private String password;
	private String mobileNumber;
	private int status;
	private Date createdOn;
	private String createdBy;
	private String accessToken;
	private String deviceId;
	private UserType userType;
	private String userTypeVal;
	private List<UserCategory> userCategory;
	private List<UserDataCollection> userDataCollection;
	private PricingDetails pricingDetails;
	private List<BookingHistory> bookingHistory;
	private List<UserLocality> userLocality;
	private List<Review> reviews;
	
	
	
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
	
	public List<UserDataCollection> getUserDataCollection() {
        return userDataCollection;
    }
    public void setUserDataCollection(List<UserDataCollection> userDataCollection) {
        this.userDataCollection = userDataCollection;
    }
    public List<BookingHistory> getBookingHistory() {
		return bookingHistory;
	}
	public void setBookingHistory(List<BookingHistory> bookingHistory) {
		this.bookingHistory = bookingHistory;
	}
	public List<UserLocality> getUserLocality() {
		return userLocality;
	}
	public void setUserLocality(List<UserLocality> userLocality) {
		this.userLocality = userLocality;
	}
	public PricingDetails getPricingDetails() {
		return pricingDetails;
	}
	public void setPricingDetails(PricingDetails pricingDetails) {
		this.pricingDetails = pricingDetails;
	}
	public String getUserTypeVal() {
		return userTypeVal;
	}
	public void setUserTypeVal(String userTypeVal) {
		this.userTypeVal = userTypeVal;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return firstName;
	}
	public void setUserName(String userName) {
		this.firstName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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
	
	public void validate(){
		
	}
    public List<UserCategory> getUserCategory() {
        return userCategory;
    }
    public void setUserCategory(List<UserCategory> userCategory) {
        this.userCategory = userCategory;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userAddress=" + userAddress + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber
                        + ", status=" + status + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", accessToken=" + accessToken + ", deviceId=" + deviceId + ", userType=" + userType + ", userTypeVal="
                        + userTypeVal + ", userCategory=" + userCategory + ", userDataCollection=" + userDataCollection + ", pricingDetails=" + pricingDetails + ", bookingHistory=" + bookingHistory + ", userLocality="
                        + userLocality + ", reviews=" + reviews + "]";
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
	
}
