package com.dj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.Category;
import com.dj.dto.UiData;
import com.dj.service.CategoryService;
import com.dj.service.UserService;
import com.dj.service.VendorService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UnauthorizedController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	UserService userService; 
	
	@RequestMapping(value = "/categoryList",method =RequestMethod.GET)
	public ResponseEntity<UiData> fetchCategoryList() {
		UiData data =  new UiData();
			try {
				List<Category> categories = categoryService.fetchCategoryList();
				data.setMessage("SUCCESS");
				data.setSuccess(true);
				data.setData(categories);
				return new ResponseEntity<UiData>(data , HttpStatus.OK);
			}
			catch (CustomGenericException e) {
				data.setMessage(e.getExceptionMsg());
				data.setSuccess(false);
				return new ResponseEntity<UiData>(data , HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
	}
	
	@RequestMapping(value = "/verifyEmail",method =RequestMethod.GET)
	public ResponseEntity<UiData> verifyEmail(@RequestParam(name ="code") String code,
												@RequestParam(name ="type") String type,
												@RequestParam(name ="id") Long id) throws CustomGenericException{
		UiData data =  new UiData();
		boolean isVerified = false;
		if(type.equals("vendor")) {
			isVerified = vendorService.verifyEmail(id,code);
		}
		else if(type.equals("user")) {
			isVerified = userService.verifyEmail(id,code);
		}
		else {
			throw new CustomGenericException("INVALID_USER_TYPE",HttpStatus.BAD_REQUEST);
		}
		
		String message  = isVerified ? "SUCCESS" : "ERROR";
		data.setMessage(message);
		data.setSuccess(isVerified);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}

}
