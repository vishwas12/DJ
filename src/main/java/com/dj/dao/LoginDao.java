package com.dj.dao;

import com.dj.dto.Login;
import com.dj.dto.User;

public interface LoginDao {
	public User validateLogin(Login login);
	
	public boolean invalidateLogin(Long userId);
	
	public boolean userAlreadyLoggedIn(Long useId);
	
	public boolean validateToken(String accessToken);
}
