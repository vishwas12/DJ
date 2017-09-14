package com.dj.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Column(name = "VERIFICATION_CODE_CREATED_DATE")
	private Timestamp verificationCodeCreatedDate;
	
	@Column(name = "RESET_PASSWORD_CODE")
	private String resetPasswordCode;
	
	@Column(name = "RESET_PASSWORD_CREATED_DATE")
	private Timestamp resetPasswordCreatedDate;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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


	public Timestamp getVerificationCodeCreatedDate() {
		return verificationCodeCreatedDate;
	}

	public void setVerificationCodeCreatedDate(Timestamp verificationCodeCreatedDate) {
		this.verificationCodeCreatedDate = verificationCodeCreatedDate;
	}

	public Timestamp getResetPasswordCreatedDate() {
		return resetPasswordCreatedDate;
	}

	public void setResetPasswordCreatedDate(Timestamp resetPasswordCreatedDate) {
		this.resetPasswordCreatedDate = resetPasswordCreatedDate;
	}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}

}
