package com.dj.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.User;
import com.dj.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenStore authStore;

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
        try{
        	user = userService.getUserByUsername(username);
        	//passwordMatch=sp.matches(password, user.getPassword());
        	passwordMatch = password.equals(user.getPassword());
        }catch(CustomGenericException e){
        	throw new BadCredentialsException("Invalid Login Credentials");
        }
        
        if(!passwordMatch){
        	throw new BadCredentialsException("Invalid Login Credentials");
        }else{
        	List<GrantedAuthority> grantedAuths = new ArrayList<>();
        	//fetch user roles from database
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));    
            Authentication auth = new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
            
    		return auth;
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public void logoutUser() throws AuthenticationException{
		OAuth2Authentication a = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		OAuth2AccessToken accessToken = authStore.getAccessToken(a);
		authStore.removeAccessToken(accessToken);
		OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
		authStore.removeRefreshToken(refreshToken);
	}

}
