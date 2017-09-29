package com.dj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dao.CategoryRepository;
import com.dj.dto.Category;
import com.dj.model.CategoryDto;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryDto> fetchCategoryList() {
		
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> dtos = new ArrayList<>();
		for(Category category : categories) {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(category, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
