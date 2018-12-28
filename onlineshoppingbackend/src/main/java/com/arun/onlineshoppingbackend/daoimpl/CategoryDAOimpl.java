package com.arun.onlineshoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arun.onlineshoppingbackend.dao.CategoryDAO;
import com.arun.onlineshoppingbackend.dto.Category;
@Repository("CategoryDAO")
@Transactional

public class CategoryDAOimpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
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
		
		return categories;
		
	}

	@Override
	public Category get(int id) {
		//enhanced for loop
		for(Category category:categories )
		{
			if(category.getId()== id) return category;
			System.out.println(id);
		}
		
		return null;
	}

	@Override
	@Transactional
	public boolean add(Category category) {
	try
	{
		//add the category to the data base
		sessionFactory.getCurrentSession().persist(category);
		return true;
	}
	catch(Exception ex) {
		ex.printStackTrace();
		return false;
	}
		
	}

}
