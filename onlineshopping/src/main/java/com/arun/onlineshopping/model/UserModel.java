package com.arun.onlineshopping.model;

import java.io.Serializable;

import  com.arun.onlineshoppingbackend.dto.Cart;

public class UserModel implements Serializable {

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", fullName=" + fullName + ", role=" + role + ", cart=" + cart + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String fullName;
	private String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private Cart cart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
		
}
