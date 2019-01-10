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
		System.out.println("refreshed");
		
		categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
		System.out.println("get bean");
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
	/*@Test
	public void testGetCategory()
	{
		 	category=categoryDAO.get(3);
	
			 
			assertEquals("Successfuly fetched a single   Category from the  table ","Television",category.getName());
			
	}*/
	/*@Test
	public void testUpdateCategory()
	{
		 	category=categoryDAO.get(3);
		 	category.setName("TV");
			
			assertEquals("Successfuly fetched a single   Category from the  table ","Television",category.getName());
			
	}*/
	//@Test
	/*public void testUpdateCategory()
	{
		 	category=categoryDAO.get(3);
		 	
			
			assertEquals("Successfuly deleted  a single   Category from the  table ",true,categoryDAO.delete(category));
			
	}*/
	/*@Test
	public void testUpdateCategory()
	{
	
		 	
			
			assertEquals("Successfuly fetched the list of  Category from the  table ",3,categoryDAO.list().size());
			
	}*/
	
}
