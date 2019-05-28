package com.arun.onlineshoppingbackend.dao;

import java.util.List;

import com.arun.onlineshoppingbackend.dto.Address;
import com.arun.onlineshoppingbackend.dto.Cart;
import com.arun.onlineshoppingbackend.dto.User;



public interface UserDAO {

	// user related operation
	

	boolean addUser(User user);
	
	User getByEmail(String email);
	//User getById(int id);
	// adding and updating a new address
	
	boolean addAddress(Address address);
	Address getBillingAdress(User user);
	List<Address> listShippingAddresses(User user);
	
	//alternative soltion it will not generate more queries to get output

	//Address getBillingAdress(int  userId);
	//List<Address> listShippingAddresses(int  userId);
}
