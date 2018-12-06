package com.arun.onlineshoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.arun.onlineshoppingbackend.dao.CategoryDAO;
import com.arun.onlineshoppingbackend.dto.Category;
@Repository("CategoryDAO")
public class CategoryDAOimpl implements CategoryDAO {
	private static List<Category> categories=new ArrayList<>();
	static {
		//first category
		Category category=new Category();
		category.setId(1);
		category.setName("TV");
		category.setDescription("samsung");
		category.setImageURL("cat.png");
		categories.add(category);
		
		//first category
				 category=new Category();
				category.setId(2);
				category.setName("MOB");
				category.setDescription("samsung");
				category.setImageURL("cat.png");
				categories.add(category);
				//first category
				 category=new Category();
				category.setId(3);
				category.setName("LAP");
				category.setDescription("samsung");
				category.setImageURL("cat.png");
				categories.add(category);
		
	}

	@Override
	public List<Category> list() {
		
		System.out.println(categories.size());
		
		return categories;
		
	}

}
