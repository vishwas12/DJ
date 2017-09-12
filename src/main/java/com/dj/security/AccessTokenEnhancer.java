package com.dj.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.dj.dto.AuthUser;


public class AccessTokenEnhancer implements TokenEnhancer {
	
	private List<TokenEnhancer> delegates = Collections.emptyList();

	public void setTokenEnhancers(List<TokenEnhancer> delegates) {
	    this.delegates = delegates;
	}

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;
	     AuthUser authUser = (AuthUser)authentication.getUserAuthentication().getPrincipal();
	     
	     final Map<String, Object> additionalInformation = new HashMap<String, Object>();
	     List<String> roles= new ArrayList<String>();
	     roles.add("ROLE_USER");
	     additionalInformation.put("roles", roles);
	     tempResult.setAdditionalInformation(additionalInformation);

	     OAuth2AccessToken result = tempResult;
	     for (TokenEnhancer enhancer : delegates) {
	    	 result = enhancer.enhance(result, authentication);
	     }
	     return result;
	}

}
