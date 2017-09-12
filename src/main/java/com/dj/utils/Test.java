package com.dj.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		String password ="password";
		String email = "ahuja.sagar17@gmail.com";
		Md5PasswordEncoder md5PwdEncoder = new Md5PasswordEncoder();
	    md5PwdEncoder.setEncodeHashAsBase64(true);
	    ShaPasswordEncoder shaPwdEncoder = new ShaPasswordEncoder(512);
	    shaPwdEncoder.setEncodeHashAsBase64(true );
		String pwd =  shaPwdEncoder.encodePassword(md5PwdEncoder.encodePassword(password, email), password+email);
		System.out.println(pwd);
		System.out.println(pwd.equals("TZqhYsJ2VLNJ3UoZmjetXemVHXZr8ffs0IMo6ob8Yx6pM/1+99GEQIMHWUjBFCjJzziaWrIDfEUzmq8HmzGz5Q=="));
	}

}
