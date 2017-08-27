package com.dj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.dj.dto.User;
import com.dj.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println(authentication.getCredentials());
		System.out.println(authentication.getPrincipal());
		
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        StandardPasswordEncoder sp = new StandardPasswordEncoder();
        
        boolean passwordMatch=false;
        User user = null;
        
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
