package com.dj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.UiData;
import com.dj.model.CategoryDto;
import com.dj.model.LocationsDto;
import com.dj.service.CategoryService;
import com.dj.service.CommonService;
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
	
	@Autowired
	CommonService commonService;
	
	@RequestMapping(value = "/categoryList",method =RequestMethod.GET)
	public ResponseEntity<UiData> fetchCategoryList() {
		UiData data =  new UiData();
			try {
				List<CategoryDto> categories = categoryService.fetchCategoryList();
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
	
	@RequestMapping(value = "/forgotPassword",method =RequestMethod.POST)
	public ResponseEntity<UiData> forgotPassword(@RequestParam("email")String email ) throws CustomGenericException{
		UiData data =  new UiData();
		commonService.sendPasswordResetMail(email);
		data.setMessage("SUCCESS");
		data.setSuccess(true);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/verifyPasswordLink",method =RequestMethod.GET)
	public ResponseEntity<UiData> verifyPasswordLink(@RequestParam(name ="code") String code,
												@RequestParam(name ="type") String type,
												@RequestParam(name ="id") Long id) throws CustomGenericException{
		UiData data =  new UiData();
		boolean isVerified = false;
		if(type.equals("vendor")) {
			isVerified = vendorService.verifyPasswordLink(id,code);
		}
		else if(type.equals("user")) {
			isVerified = userService.verifyPasswordLink(id,code);
		}
		else {
			throw new CustomGenericException("INVALID_USER_TYPE",HttpStatus.BAD_REQUEST);
		}
		
		String message  = isVerified ? "SUCCESS" : "ERROR";
		data.setMessage(message);
		data.setSuccess(isVerified);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	@RequestMapping(value = "/resetPassword",method =RequestMethod.POST)
	public ResponseEntity<UiData> resetPassword(@RequestBody Map<String,String> map) throws CustomGenericException{
		UiData data =  new UiData();
		commonService.resetPassword(map);
		data.setMessage("SUCCESS");
		data.setSuccess(true);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validateEmail",method =RequestMethod.GET)
	public ResponseEntity<UiData> verifyEmail(@RequestParam String email) throws CustomGenericException{
		UiData data =  new UiData();
		boolean isAlreadyExists = commonService.verifyEmail(email);
		data.setMessage("SUCCESS");
		data.setSuccess(true);
		data.setData(isAlreadyExists);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	@RequestMapping(value = "/fetchLocations",method =RequestMethod.GET)
	public ResponseEntity<UiData> fetchLocations(@RequestParam String pinCode) throws CustomGenericException{
		UiData data =  new UiData();
		LocationsDto dto = commonService.fetchLocations(pinCode);
		data.setMessage("SUCCESS");
		data.setSuccess(true);
		data.setData(dto);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}

}
