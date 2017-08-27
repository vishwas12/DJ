package com.dj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.ApplicationDao;
import com.dj.dao.UserDao;
import com.dj.dto.Mail;
import com.dj.dto.MusicType;
import com.dj.dto.User;
import com.dj.dto.UserType;
import com.dj.service.UserService;
import com.dj.utils.Mailer;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ApplicationDao applicationDao;
	
	@Autowired
	Mailer mailer;
	
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
		if(!userDao.emailValidate(user.getEmail())){
			UserType userType = applicationDao.getUserType(user.getUserTypeVal());
			user.setUserType(userType);
			userDao.registerUser(user);
			mailer.sendEmail(user);
			
		}
		else{
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
	

}
