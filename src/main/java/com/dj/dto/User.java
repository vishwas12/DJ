package com.dj.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="USER")
@DynamicUpdate
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
	private Long userId;
	private String firstName;
	private String lastName;
	private String userAddress;
	@Column(unique=true)
	private String email;
	private String password;
	private String mobileNumber;
	private Integer status;
	private Date createdOn;
	private String createdBy;
	private String accessToken;
	private String deviceId;
	//private UserType userType;
	private String userTypeVal;
	
	@Column(name = "IS_EMAIL_VERIFIED")
	private Boolean isEmailVerified;
	/*private List<UserCategory> userCategory;
	private List<UserDataCollection> userDataCollection;
	private PricingDetails pricingDetails;
	private List<BookingHistory> bookingHistory;
	private List<UserLocality> userLocality;
	private List<Review> reviews; */
	@Transient
	private List<String> roles;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserVerification userVerification;
	
	
	
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
	
/*	public List<UserDataCollection> getUserDataCollection() {
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
	}*/
	public String getUserTypeVal() {
		return userTypeVal;
	}
	public void setUserTypeVal(String userTypeVal) {
		this.userTypeVal = userTypeVal;
	}
	/*public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}*/
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void validate(){
		
	}
    /*public List<UserCategory> getUserCategory() {
        return userCategory;
    }
    public void setUserCategory(List<UserCategory> userCategory) {
        this.userCategory = userCategory;
    }
    public List<Review> getReviews() {
        return reviews;
    } */
    
    public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public UserVerification getUserVerification() {
		return userVerification;
	}
	public void setUserVerification(UserVerification userVerification) {
		this.userVerification = userVerification;
	}
	/*@Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userAddress=" + userAddress + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber
                        + ", status=" + status + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", accessToken=" + accessToken + ", deviceId=" + deviceId + ", userType=" + userType + ", userTypeVal="
                        + userTypeVal + ", userCategory=" + userCategory + ", userDataCollection=" + userDataCollection + ", pricingDetails=" + pricingDetails + ", bookingHistory=" + bookingHistory + ", userLocality="
                        + userLocality + ", reviews=" + reviews + "]";
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }*/
	
}
