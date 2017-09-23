package com.dj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.ApplicationDao;
import com.dj.dao.AuthUserRepository;
import com.dj.dao.RoleRepository;
import com.dj.dao.UserDao;
import com.dj.dao.UserRepository;
import com.dj.dao.UserVerificationRepository;
import com.dj.dto.AuthUser;
import com.dj.dto.MusicType;
import com.dj.dto.Role;
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
	RoleRepository roleRepository;
	
	@Autowired
	UserVerificationRepository userVerificationRepository;
	
	@Autowired
	AuthUserRepository authUserRepository;
	
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
			Role role = roleRepository.findByRole(Constants.ROLE_USER);
			user.getRoles().add(role);
			user = userRepository.save(user);
			insertAuthUser(user);
			mailer.prepareEmail(user,Constants.EMAIL_VERIFICATION,"Account Verification");
			
		}else{
			throw new CustomGenericException("Email Already Taken", HttpStatus.BAD_REQUEST);
		}
	}

	private void insertAuthUser(User user) {
		AuthUser authUser = new AuthUser();
		authUser.setEmail(user.getEmail());
		authUser.setFirstName(user.getFirstName());
		authUser.setLastName(user.getLastName());
		authUser.setPassword(user.getPassword());
		List<String> roles = new ArrayList<>();
		for(Role role : user.getRoles()) {
			roles.add(role.getRole());
		}
		authUser.setRoles(StringUtils.join(roles, ","));
		authUserRepository.save(authUser);
		
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
	public boolean verifyPasswordLink(Long id, String code) {
		UserVerification uvf = userVerificationRepository.findByUserId(id);
		boolean isVerified = false;
		if(null != uvf) {
			if(null != uvf.getResetPasswordCode() && uvf.getResetPasswordCode().equals(code)) {
				isVerified =true;
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
	

}
