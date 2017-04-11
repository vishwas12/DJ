package com.dj.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dj.application.util.AESEncrypter;
import com.dj.dao.LoginDao;
import com.dj.dao.UserDao;
import com.dj.dto.Login;
import com.dj.dto.User;
import com.dj.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public User validateLogin(Login login) {
		User user = new User();
		StringBuffer accessToken = new StringBuffer();
		
		user =  loginDao.validateLogin(login);
		
		accessToken.append(user.getUserId()).append("###");
		accessToken.append(login.getEmail()).append("###");
		accessToken.append(login.getPassword()).append("###");
		accessToken.append(UUID.randomUUID());
		
		if("9999".equals(login.getDeviceId())){
			loginDao.invalidateLogin(user.getUserId());
		}
		
		if(!StringUtils.isEmpty(accessToken.toString())){
			try {
				user.setAccessToken(AESEncrypter.encrypt(accessToken.toString()));
			} catch (Exception e) {
			}
		}
			if("9999".equals(login.getDeviceId()) || !loginDao.userAlreadyLoggedIn(user.getUserId())){
				user.setDeviceId(login.getDeviceId());
				userDao.insertUserLoginDetails(user);
			}
		return user;
	}

	@Override
	public boolean validateToken(String accessToken) {
		return loginDao.validateToken(accessToken);
	}

}
