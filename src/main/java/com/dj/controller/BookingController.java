package com.dj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.User;
import com.dj.service.BookingService;
import com.google.gson.Gson;

@RestController("/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	Gson gson;
	
	@RequestMapping(value = "/bookBand", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> bookBand(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		User user = new User();
		user = gson.fromJson((String)request.getAttribute("BODY"), User.class);
		bookingService.bookBand(user);
		map.put("message", "Band Booked");
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.OK);
	}
	
/*	@RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> cancelBooking(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		User user = new User();
		user = gson.fromJson((String)request.getAttribute("BODY"), User.class);
		bookingService.cancelBooking(user.getBookingHistory().get(0).getBookingHistoryId());
		map.put("message", "Band Cancelled");
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/viewBooking", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> viewBooking(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		User user = new User();
		user = gson.fromJson((String)request.getAttribute("BODY"), User.class);
		bookingService.viewBooking(user);
		map.put("message", "Band Booked");
		return new ResponseEntity<Map<String,String>>(map , HttpStatus.OK);
	}
	
	@ExceptionHandler({CustomGenericException.class})
	public ResponseEntity<Map<String, String>> handleException(CustomGenericException ex){
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", ex.getExceptionMsg());
		return new ResponseEntity<Map<String, String>>(map , ex.getExceptionCode());
	}
}
