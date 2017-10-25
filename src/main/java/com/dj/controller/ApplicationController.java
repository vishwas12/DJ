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
import com.dj.dto.AuthUser;
import com.dj.dto.MusicType;
import com.dj.dto.UiData;
import com.dj.dto.User;
import com.dj.security.CustomAuthenticationProvider;
import com.dj.security.OAuthSession;
import com.dj.service.BookingService;
import com.dj.service.UserService;
import com.dj.utils.Mailer;
import com.google.gson.Gson;

@RestController
@RequestMapping(value="/user")
public class ApplicationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    BookingService bookingService;
	
	@Autowired
    Gson gson;
	
	@Autowired
	Mailer mailer;
	
	@Autowired
	OAuthSession oAuthSession;
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UiData> loginUser(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		UiData data = new UiData();
		data.setData(user.getAccessToken());
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<UiData> logout(){
		//String accessToken = request.getHeader("accessToken");
		customAuthenticationProvider.logoutUser();
		UiData data = new UiData();
		data.setMessage("User Logged Out");
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	
/*	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public ResponseEntity<UiData> sendMail(HttpServletRequest request){
		//String accessToken = request.getHeader("accessToken");
		//userService.logout(accessToken);
		UiData data = new UiData();
		//Mailer m =  new Mailer();
		User user =  new User();
		user.setFirstName("Dummy Name");
		user.setEmail("ahuja.sagar14@gmail.com");
		mailer.sendEmail(user);
		data.setMessage("User Logged Out");
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<UiData> registerUser(@RequestBody User user){
		UiData data = new UiData();
		try {
			userService.registerUser(user);
			data.setMessage("User Registered");
		}
		catch(CustomGenericException e) {
			data.setMessage(e.getExceptionMsg());
			return new ResponseEntity<UiData>(data , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllMusicTypes", method = RequestMethod.POST)
	public ResponseEntity<UiData> getAllMusicTypes(){
		AuthUser user = oAuthSession.getAuthUser();
		//validate user (can be handled via annotation)
		
		Map<String, String> map = new HashMap<String, String>();
		List<MusicType> list = new ArrayList<MusicType>();
		list = userService.getAllMusicTypes();
		UiData data = new UiData();
		data.setData(list);
		return new ResponseEntity<UiData>(data , HttpStatus.OK);
	}
/*	@RequestMapping(value = "/viewBandDetails", method = RequestMethod.POST)
    public ResponseEntity<Map<String, SearchResults>> viewBandDetails(@RequestBody String request) {
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> myMap = gson.fromJson(request, type);
        Map<String, SearchResults> map = bookingService.viewBandDetails(myMap.get("location"), myMap.get("category"));
        if (MapUtils.isNotEmpty(map)) {
            return new ResponseEntity<Map<String, SearchResults>>(map, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Map<String, SearchResults>>(map, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }*/
	
	@ExceptionHandler({CustomGenericException.class})
	public ResponseEntity<Map<String, String>> handleException(CustomGenericException ex){
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", ex.getExceptionMsg());
		return new ResponseEntity<Map<String, String>>(map , ex.getExceptionCode());
	}
}
