package com.dj.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_VERIFICATION")
public class UserVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_VERIFICATION_ID")
	private Long userVerificationId;
	
	@Column(name = "VERIFICATION_CODE")
	private String verificationCode; 
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	public Long getUserVerificationId() {
		return userVerificationId;
	}

	public void setUserVerificationId(Long userVerificationId) {
		this.userVerificationId = userVerificationId;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	
	

}
