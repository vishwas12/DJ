package com.dj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.UiData;
import com.dj.dto.Vendor;
import com.dj.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
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
}
