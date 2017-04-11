package com.dj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.MusicType;
import com.dj.dto.User;
import com.dj.service.UserService;

@RestController
@RequestMapping(value="/user")
public class ApplicationController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> loginUser(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>(); 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		
		map.put("accessToken", user.getAccessToken());
		return new ResponseEntity<Map<String, String>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> logout(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		String accessToken = request.getHeader("accessToken");
		userService.logout(accessToken);
		map.put("message", "User Logged Out");
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user){
		Map<String, String> map = new HashMap<String, String>();
		userService.registerUser(user);
		map.put("message", "User Registered");
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllMusicTypes", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> getAllMusicTypes(){
		Map<String, String> map = new HashMap<String, String>();
		List<MusicType> list = new ArrayList<MusicType>();
		list = userService.getAllMusicTypes();
		map.put("message", "User Registered");
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.OK);
	}
	
	
	@ExceptionHandler({CustomGenericException.class})
	public ResponseEntity<Map<String, String>> handleException(CustomGenericException ex){
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", ex.getExceptionMsg());
		return new ResponseEntity<Map<String, String>>(map , ex.getExceptionCode());
	}
}
