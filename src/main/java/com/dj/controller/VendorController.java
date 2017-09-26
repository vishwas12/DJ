package com.dj.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.AuthUser;
import com.dj.dto.UiData;
import com.dj.dto.Vendor;
import com.dj.model.VendorDto;
import com.dj.security.OAuthSession;
import com.dj.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	OAuthSession oAuthSession;
	
	@RequestMapping(value = "/Signup",method =RequestMethod.POST)
	public ResponseEntity<UiData> vendorSignup(@RequestBody Vendor vendor, BindingResult results) {
		UiData data =  new UiData();
		if(results.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else
		{
			try {
				vendorService.vendorSignUp(vendor);
				data.setMessage("SUCCESS");
				data.setSuccess(true);
				return new ResponseEntity<UiData>(data , HttpStatus.OK);
			}
			catch (CustomGenericException e) {
				data.setMessage(e.getExceptionMsg());
				data.setSuccess(false);
				return new ResponseEntity<UiData>(data , HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		}
	}
	
	@RequestMapping(value = "/details",method =RequestMethod.GET)
	public ResponseEntity<UiData> vendorDetails(OAuth2Authentication authentication) {
		AuthUser user  = (AuthUser) authentication.getUserAuthentication().getPrincipal();
		UiData data = new UiData();
		try {
			VendorDto vendor = vendorService.fetchDetails(user.getUserId());
			data.setData(vendor);
			data.setMessage("SUCCESS");
			data.setSuccess(true);
			return new ResponseEntity<UiData>(data, HttpStatus.OK);
		} catch (CustomGenericException e) {
			data.setMessage(e.getExceptionMsg());
			data.setSuccess(false);
			return new ResponseEntity<UiData>(data, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
