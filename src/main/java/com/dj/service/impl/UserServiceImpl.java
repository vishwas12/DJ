package com.dj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.ApplicationDao;
import com.dj.dao.UserDao;
import com.dj.dao.UserRepository;
import com.dj.dto.MusicType;
import com.dj.dto.User;
import com.dj.service.UserService;
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
			mailer.prepareEmail(user);
			
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
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	

}
