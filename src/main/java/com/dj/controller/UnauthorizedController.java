package com.dj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.Category;
import com.dj.dto.UiData;
import com.dj.service.CategoryService;

@RestController
@RequestMapping("/api")
public class UnauthorizedController {
	
	@Autowired
	CategoryService categoryService;
	
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

}
