package com.dj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dao.CategoryRepository;
import com.dj.dto.Category;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> fetchCategoryList() {
		
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

}
