package com.arun.onlineshoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.query.Query;
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

	@Override
	public List<Category> list() {
		
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query=sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		
		return query.getResultList();
	}
 
	/*
	 * 
	 */
	@Override
	public Category get(int id) {
		// enhanced for loop

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {
		try {
			// add the category to the data base
			System.out.println("product added ");
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
//updating a single category
	@Override
	public boolean update(Category category) {
		try {
			// add the category to the data base
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
	
		return false;
	}

}
