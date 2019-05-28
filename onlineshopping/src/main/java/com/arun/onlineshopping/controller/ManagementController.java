package com.arun.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.arun.onlineshopping.util.FileUploadUtility;
import com.arun.onlineshopping.validator.ProductValidator;
import com.arun.onlineshoppingbackend.dao.CategoryDAO;
import com.arun.onlineshoppingbackend.dao.ProductDAO;
import com.arun.onlineshoppingbackend.dto.Category;
import com.arun.onlineshoppingbackend.dto.Product;

@Controller
@RequestMapping("/manages")
public class ManagementController {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	private static final Logger logger=LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("useClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		logger.info("bande illige");
		if (operation != null) {
			logger.info("operation alli value ide");
			if (operation.equals("product")) {
				logger.info("operation annodu product ge equal agide");
				mv.addObject("message", "Product submitted successfully");
			}
			else if(operation.equals("category"))
			{
				mv.addObject("message", "Category submitted successfully");
			}
		}

		return mv;

	}

	// Handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request) {
		if(mProduct.getId()==0){
		new ProductValidator().validate(mProduct, results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals(""))
			{
				new ProductValidator().validate(mProduct, results);
			}
		}
		if(results.hasErrors()){
			model.addAttribute("title", "Manage Product");
			model.addAttribute("useClickManageProducts", true);
			model.addAttribute("message", "Product validation failed");
			return "page";
		}
		logger.info(mProduct.toString());
		// Create new record
		if(mProduct.getId()==0){
		productDAO.add(mProduct);
		}
		else{
			productDAO.update(mProduct);
		
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		

		return "redirect:/manages/products?operation=product";
	}
	
@RequestMapping(value ="/product/{id}/activation",method=RequestMethod.POST)
@ResponseBody
public String handleProductActivation(@PathVariable int id){
	//is fetch the product details from the DB
	System.out.println("Activation handler method called");
	Product product=productDAO.get(id);
	Boolean isActive=product.isActive();
	product.setActive(!product.isActive());
	productDAO.update(product);
	
	
	return (isActive)? "You have successfully deactivated the product with id" +product.getId()
	:"You have successfully deactivated the product with id" +product.getId();
}

@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
public ModelAndView showEditProductDetails(@PathVariable int id ) {

	ModelAndView mv = new ModelAndView("page");
	mv.addObject("useClickManageProducts", true);
	mv.addObject("title", "Manage Products");
	//fetch the product from the database
	Product nProduct = productDAO.get(id);
	
	//set the product fetch from the db
	mv.addObject("product", nProduct);
	logger.info("bande illige");
	
	
	return mv;
	
	}
@RequestMapping(value = "/category", method=RequestMethod.POST)
public String managePostCategory(@ModelAttribute Category mCategory) {					
	categoryDAO.add(mCategory);		
	return "redirect:/manages/products?operation=category";
}


	@ModelAttribute("categories")
	public List<Category> getCategory() {
		logger.info("it return the categories bartide");
		System.out.println("------Category print madtidini "+categoryDAO.list());
		return categoryDAO.list();

	}
	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}

}
