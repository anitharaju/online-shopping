package com.arun.onlineshoppingbackend.dao;

import java.util.List;

import com.arun.onlineshoppingbackend.dto.Category;

public interface CategoryDAO {
	boolean add(Category category);

	List<Category> list();
	Category get(int id);
 }
