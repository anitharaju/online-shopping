package com.arun.onlineshopping.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arun.onlineshopping.exception.ProductNotFoundException;
import com.arun.onlineshoppingbackend.dao.CategoryDAO;
import com.arun.onlineshoppingbackend.dao.ProductDAO;
import com.arun.onlineshoppingbackend.dto.Category;
import com.arun.onlineshoppingbackend.dto.Product;

@Controller
public class PageController {
	public static final Logger logger=LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@RequestMapping(value = { "/", "/home", "/index" }/*path="/",method=RequestMethod.GET*/)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		logger.info("pageController index method - INFO");
		logger.debug("pageController index method - DEBUG");

		mv.addObject("userClickHome", true);
  		mv.addObject("categories", categoryDAO.list());
  		
  		
		return mv;
		
	}
	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}

	/*
	 * methods to load all the products
	 */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", " All Products");
		mv.addObject("userClickAllProducts", true);

		mv.addObject("categories", categoryDAO.list());

		return mv;
	}
	/*
	 * to display the product by category
	 * 
	 */

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)  {

		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch a single category
		Category category = null;

		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		// passing the single category object
		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	/*
	 * to display the product in a single view
	 */
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv =new ModelAndView("page");
		 
		Product product= productDAO.get(id);
		
		if(product==null)
		{
			throw new ProductNotFoundException();
		}
		product.getId();
		
		//update the view count
		product.setViews(product.getViews()+1);
		
		productDAO.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct",true);
	    return mv;
		
	}
	@RequestMapping(value = { "/register" })
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "register Us");
		
		
		return mv;
	}
	@RequestMapping(value = { "/login" })
	public ModelAndView login(@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false)String logout) {
		ModelAndView mv = new ModelAndView("login");
		if(error!=null){
			mv.addObject("message", "Invalid username and password");
			
		}
		
		if(logout!=null){
			mv.addObject("logout", "User has successfully logged out");
			
		}
		
		mv.addObject("title", "Login Page");
	;
		return mv;
	}
	//access denied page
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 Access Denied");
		mv.addObject("errorTitle", "Aha!  CAUGHT YOU");
		mv.addObject("errorDescription", "you are not autherise to view this page!");
		return mv;
	}
	//logout
	@RequestMapping(value ="/perform-logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//first we are going to fetch the authentication
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null){
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
		
	}
	
}
