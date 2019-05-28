package com.arun.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.onlineshopping.model.UserModel;
import com.arun.onlineshoppingbackend.dao.CartLineDAO;
import com.arun.onlineshoppingbackend.dao.ProductDAO;
import com.arun.onlineshoppingbackend.dto.Cart;
import com.arun.onlineshoppingbackend.dto.CartLine;
import com.arun.onlineshoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private ProductDAO productDAO;
	
	
	
	//return cart of the user who has logged in 
	private Cart getCart(){
	
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine>  getCartLines(){
		
		
		return cartLineDAO.list(this.getCart().getId());
	}

	/* to update the cart count */
	public String manageCartLine(int cartLineId, int count) {
		
		CartLine cartLine = cartLineDAO.get(cartLineId);		

		double oldTotal = cartLine.getTotal();

		
		Product product = cartLine.getProduct();
		
		// check if that much quantity is available or not
		if(product.getQuantity() < count) {
			return "result=unavailable";		
		}	
		
		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * count);
		cartLineDAO.update(cartLine);

	
		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartLineDAO.updateCart(cart);
		
		return "result=updated";
	}
	public String deleteCartLine(int cartLineId) {
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		// deduct the cart
		// update the cart
		Cart cart = this.getCart();	
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);		
		cartLineDAO.updateCart(cart);
		
		// remove the cartLine
		cartLineDAO.remove(cartLine);
				
		return "result=deleted";
	}
	public String addCartLine(int productId) {		
		Cart cart = this.getCart();
		String response = null;
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine==null) {
			// add a new cartLine if a new product is getting added
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			// transfer the product details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice());
			
			// insert a new cartLine
			cartLineDAO.add(cartLine);
			
			// update the cart
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
			cartLineDAO.updateCart(cart);

			response = "result=added";						
		} 
		else{
			if(cartLine.getProductCount() < 3){
				response=this.manageCartLine(cartLine.getId(),cartLine.getProductCount() + 1);
				
			}
			else{
				
			
			response= "result=maximum";
		}
		}
		return response;
	}
	
}
