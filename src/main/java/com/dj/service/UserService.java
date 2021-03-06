package com.dj.service;

import java.util.List;
import java.util.Map;

import com.dj.dto.AuthUser;
import com.dj.dto.MusicType;
import com.dj.dto.User;

public interface UserService {
	
	public User getUserByAccessToken(String accessToken);
	public AuthUser getUserByUsername(String username);
	public boolean logout(String accessToken);
	public void registerUser(User user);
	public List<MusicType> getAllMusicTypes();
	public List<User> getMusicians(List<MusicType> musicTypes);
	public boolean verifyEmail(Long id, String code);
	public boolean verifyPasswordLink(Long id, String code);
}
