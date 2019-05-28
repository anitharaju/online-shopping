package com.arun.onlineshopping.model;

import java.io.Serializable;

import com.arun.onlineshoppingbackend.dto.Address;
import com.arun.onlineshoppingbackend.dto.User;

public class RegisterModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Address billing;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getbilling() {
		return billing;
	}
	public void setbilling(Address billing) {
		this.billing =billing;
	}
	

}
