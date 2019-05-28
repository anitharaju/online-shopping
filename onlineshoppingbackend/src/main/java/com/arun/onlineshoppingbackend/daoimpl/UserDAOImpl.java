package com.arun.onlineshoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arun.onlineshoppingbackend.dao.UserDAO;
import com.arun.onlineshoppingbackend.dto.Address;
import com.arun.onlineshoppingbackend.dto.Cart;
import com.arun.onlineshoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email =:email";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception ex) {
			System.out.println("--------------EXECPTION FROM GET BY EMAIL ID");
			//ex.printStackTrace();
			return null;
		}

	}

	@Override
	public Address getBillingAdress(User user) {
		String selectQuery = "FROM Address WHERE user =:user AND billing =:billing";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddresses(User user) {
		String selectQuery = "FROM Address WHERE user =:user AND shipping =:shipping";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("shipping", true).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*@Override
	public Address getBillingAdress(int userId) {
		String selectQuery = "FROM Address WHERE id =:id AND billing =:billing";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("id", userId)
					.setParameter("billing", true).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		
		String selectQuery = "FROM Address WHERE id =:id AND shipping =:shipping";
		try {
			return  sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("id", userId)
					.setParameter("shipping", true).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getById(int id) {
		String selectQuery = "FROM User WHERE id =:id";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("id", id)
					.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}*/

}
