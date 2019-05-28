package com.arun.onlineshopping.handler;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.arun.onlineshopping.model.RegisterModel;
import com.arun.onlineshoppingbackend.dao.UserDAO;
import com.arun.onlineshoppingbackend.dto.Address;
import com.arun.onlineshoppingbackend.dto.Cart;
import com.arun.onlineshoppingbackend.dto.User;

@Component
public class RegisterHandler {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public RegisterModel init(){
		
		
		return new RegisterModel();
	}
	public void addUser(RegisterModel registerModel,User user){
		registerModel.setUser(user);
		
	}
	public void addBilling(RegisterModel registerModel,Address billing){
		registerModel.setbilling(billing);
		
	}
	
	//check if password matching confirm password
	
	
	public String validateUser(User user,MessageContext error){
		String transitionValue="success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match the confirm password").build());
			transitionValue="failure";
			
		}
		
		//check unickenss of email
		
		if(userDAO.getByEmail(user.getEmail())!=null){
			error.addMessage(new MessageBuilder().error().source("email")
					.defaultText("Email address is already used").build());
			transitionValue="failure";
		
		}
		
		return transitionValue;
		
	}
	
	
	public String saveAll(RegisterModel model){
		String transitionValue = "success";
		User user=model.getUser();
		if(user.getRole().equals("USER")){
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//dicrypt password encoder
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//save the user
		userDAO.addUser(user);
		
		//get the address
		Address billing =model.getbilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		userDAO.addAddress(billing);
		//save the address
		return transitionValue;
		
		
	}
}
