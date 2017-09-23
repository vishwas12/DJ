package com.dj.service;

import java.util.Map;

public interface CommonService {

	void sendPasswordResetMail(String email);

	void resetPassword(Map<String, String> map);

	boolean verifyEmail(String email);

}
