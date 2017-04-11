package com.dj.service;

import com.dj.dto.Login;
import com.dj.dto.User;

public interface ValidationService {
	public User validateLogin(Login login);
	public boolean validateToken(String accessToken);
}
