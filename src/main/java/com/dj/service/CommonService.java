package com.dj.service;

import java.util.List;
import java.util.Map;

import com.dj.model.LocationsDto;

public interface CommonService {

	void sendPasswordResetMail(String email);

	void resetPassword(Map<String, String> map);

	boolean verifyEmail(String email);

	LocationsDto fetchLocations(String pinCode);

}
