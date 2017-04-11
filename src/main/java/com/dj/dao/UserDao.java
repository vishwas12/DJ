package com.dj.dao;

import java.util.List;

import com.dj.dto.MusicType;
import com.dj.dto.User;

public interface UserDao {
	public void insertUserLoginDetails(User user);
	public User getUserByAccessToken(String accessToken);
	public boolean logout(String accessToken);
	public void registerUser(User user);
	public void registerAsUpdatedUser(User user);
	public boolean emailValidate(String email);
	public List<MusicType> getAllMusicTypes();
	public List<User> getMusicians(List<MusicType> musicTypes);
}
