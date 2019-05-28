package com.arun.onlineshoppingbackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arun.onlineshoppingbackend.dao.UserDAO;
import com.arun.onlineshoppingbackend.dto.Address;
import com.arun.onlineshoppingbackend.dto.Cart;
import com.arun.onlineshoppingbackend.dto.User;



public class UserTestCase {
       //test case
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.arun.onlineshoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
 
	/*@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
				assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		address = new Address();
		address.setAddressLineOne("line one");
		address.setAddressLineTwo("Near haralahalli");
		address.setCity("hassan");
		address.setState("hassan");
		address.setCountry("India");
		address.setPostalCode("400002");
		address.setBilling(true);
		
		
		
		// linked the address with the user
		address.setUserId(user.getId());
		// add the address
				assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));
		if(user.getRole().equals("USER")){
			//create cart for this
			 cart=new Cart();
			cart.setUser(user);
			assertEquals("Failed to add  cart!", true, userDAO.addCart(cart));
			
			// add the shipping address
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			address.setUserId(user.getId());
			address.setShipping(true);
			assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		}
		
			
		

				
		
		
	}*/
	
	

	// working for uni-directional

	/*@Test
	public void testAddAddress() {
		user = userDAO.get(1);
		
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
				
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	
	}*/
	
	/*@Test
	public void testUpdateCart() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		if(user.getRole().equals("USER")){
			//create cart for this
			 cart=new Cart();
			cart.setUser(user);
			
			//attach cart with the user
			user.setCart(cart);
		}
			//assertEquals("Failed to add  cart!", true, userDAO.addCart(cart));
		
		// add the user
				assertEquals("Failed to add the user!", true, userDAO.addUser(user));
			
	} */

/*@Test
public void testUpdateCart(){
	user=userDAO.getByEmail("hr@gmail.com");
	//user = userDAO.get(1);
			cart = user.getCart();
			cart.setGrandTotal(4000);
			cart.setCartLines(3);
			assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));		
	
}*/
	/*@Test
	public void testCaseForAddAddress(){
		//we need to add an user 
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
				assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		//we are going to add address 
				address = new Address();
				address.setAddressLineOne("line one");
				address.setAddressLineTwo("Near haralahalli");
				address.setCity("hassan");
				address.setState("hassan");
				address.setCountry("India");
				address.setPostalCode("400002");
				address.setBilling(true);
				
				//attach user to the address
				address.setUser(user);
				assertEquals("Failed to add adress ",true,userDAO.addAddress(address));
				
		//we are going add shipping address
				// add the shipping address
				address = new Address();
				address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
				address.setAddressLineTwo("Near Kudrat Store");
				address.setCity("Mumbai");
				address.setState("Maharashtra");
				address.setCountry("India");
				address.setPostalCode("400001");
				address.setShipping(true);
				//attach user to the address
				address.setUser(user);
				assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		
	}*/
	/*@Test
	public void testAddAdress(){
		user=userDAO.getByEmail("hr@gmail.com");
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Bangalore	");
		address.setState("karnataka");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		//attach user to the address
		address.setUser(user);
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));

	}*/
	@Test
	public void testgetAddress(){
		user =userDAO.getByEmail("hr@gmail.com");
		
		assertEquals("Filed to fetch the list of address and size doesnot match",2,
				userDAO.listShippingAddresses(user).size());
		assertEquals("Filed to fetch the list of billing  and size doesnot match","hassan",
				userDAO.getBillingAdress(user).getCity());
	}
	
	
}
