package com.dj.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.ApplicationDao;
import com.dj.dao.UserDao;
import com.dj.dao.UserRepository;
import com.dj.dao.UserVerificationRepository;
import com.dj.dto.AuthUser;
import com.dj.dto.MusicType;
import com.dj.dto.User;
import com.dj.dto.UserVerification;
import com.dj.dto.Vendor;
import com.dj.dto.VendorVerification;
import com.dj.service.UserService;
import com.dj.utils.Constants;
import com.dj.utils.EncryptionUtils;
import com.dj.utils.Mailer;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ApplicationDao applicationDao;
	
	@Autowired
	Mailer mailer;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserVerificationRepository userVerificationRepository;
	
	@Override
	public User getUserByAccessToken(String accessToken) {
		return userDao.getUserByAccessToken(accessToken);
	}

	@Override
	public boolean logout(String accessToken) {
		return userDao.logout(accessToken);
	}

	@Override
	public void registerUser(User user) {
		if(userRepository.countByEmail(user.getEmail()) < 1){
			user.setPassword(EncryptionUtils.passwordEncoder(user.getEmail(),user.getPassword()));
			user.setCreatedOn(new Date());
			user = userRepository.save(user);
			mailer.prepareEmail(user,Constants.EMAIL_VERIFICATION,"Account Verification");
			
		}else{
			throw new CustomGenericException("Email Already Taken", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<MusicType> getAllMusicTypes() {
		return userDao.getAllMusicTypes();
	}

	@Override
	public List<User> getMusicians(List<MusicType> musicTypes) {
		return null;
	}

	@Override
	public AuthUser getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public boolean verifyEmail(Long id, String code) {
		UserVerification uvf = userVerificationRepository.findByUserId(id);
		boolean isVerified = false;
		if(null != uvf) {
			if(null != uvf.getVerificationCode() && uvf.getVerificationCode().equals(code)) {
				isVerified =true;
				userRepository.updateEmailVerificationStatus(id);
			}
			else {
				throw new CustomGenericException("EMAIL_VERIFICATION_TOKEN_EXPIRED",HttpStatus.OK);
			}
		}
		else{
			throw new CustomGenericException("INVALID_EMAIL_VERIFICATION",HttpStatus.OK);
		}
		return isVerified;
	}

	@Override
	public void sendPasswordResetMail(Map<String, String> map) {
		try {
			User user = userRepository.findByEmail(map.get("email"));
			if(null != user) {
				 mailer.prepareEmail(user, Constants.FORGOT_PASSWORD, "Reset Password");
			}else {
				throw new CustomGenericException("INVALID_EMAIL",HttpStatus.BAD_REQUEST);
			}
			
		}
		catch(Exception e) {
			throw new CustomGenericException("INVALID_EMAIL",HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
