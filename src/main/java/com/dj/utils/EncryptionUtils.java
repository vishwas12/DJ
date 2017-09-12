package com.dj.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class EncryptionUtils {
	
	public static String passwordEncoder(String email,String password) {
		
		Md5PasswordEncoder md5PwdEncoder = new Md5PasswordEncoder();
	    md5PwdEncoder.setEncodeHashAsBase64(true);
	    ShaPasswordEncoder shaPwdEncoder = new ShaPasswordEncoder(512);
	    shaPwdEncoder.setEncodeHashAsBase64(true );
		return shaPwdEncoder.encodePassword(md5PwdEncoder.encodePassword(password, email), password+email);
	}

}
