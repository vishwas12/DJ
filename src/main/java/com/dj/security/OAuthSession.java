package com.dj.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.dj.dto.AuthUser;
import com.dj.dto.User;

@Component
public class OAuthSession {
	
	public AuthUser getAuthUser(){
		OAuth2Authentication a = (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
		if(a!=null){
			 AuthUser user = (AuthUser)a.getUserAuthentication().getPrincipal();
			 return user;
		 }
		 return null;
	}

}
