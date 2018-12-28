package com.arun.onlineshoppingbackend.test;

import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arun.onlineshoppingbackend.dao.CategoryDAO;
import com.arun.onlineshoppingbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private static Category category;
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.arun.onlineshoppingbackend");
		context.refresh();
		
		categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
	}
	@Test
	public void testAddCategory()
	{
		 	category=new Category();
		 	category.setName("TV");
			category.setDescription("samsung");
			category.setImageURL("cat.png");
			assertEquals("Successfuly added the Category details to database table ",true,categoryDAO.add(category));
			
	}
}
