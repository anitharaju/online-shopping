package com.arun.onlineshoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arun.onlineshoppingbackend.dao.CartLineDAO;
import com.arun.onlineshoppingbackend.dao.ProductDAO;
import com.arun.onlineshoppingbackend.dao.UserDAO;
import com.arun.onlineshoppingbackend.dto.Cart;
import com.arun.onlineshoppingbackend.dto.CartLine;
import com.arun.onlineshoppingbackend.dto.Product;
import com.arun.onlineshoppingbackend.dto.User;



public class CartLineTestCase {

	

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	private Product product=null;
	private User user=null;
	private Cart cart=null;
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.arun.onlineshoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("ak@gmail.com");		
		Cart cart = user.getCart();
		
		// fetch the product 
		 product = productDAO.get(1);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
	
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
		
		double oldTotal = cartLine.getTotal();		
		
		
		
		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal()));
		
		
		assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart));
		
	}
	
	
	
	/*@Test
	public void testUpdateCartLine() {

		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("absr@gmail.com");		
		Cart cart = user.getCart();
				
		cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
				
		double oldTotal = cartLine.getTotal();
				
		cartLine.setTotal(cartLine.getProduct().getUnitPrice() * cartLine.getProductCount());
		
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to update the CartLine!",true, cartLineDAO.update(cartLine));	

		
	}*/
	
	
	
}
