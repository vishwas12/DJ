package com.dj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dj.dto.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
