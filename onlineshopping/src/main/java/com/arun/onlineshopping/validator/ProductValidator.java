package com.arun.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.arun.onlineshoppingbackend.dto.Product;

import ch.qos.logback.core.net.SyslogOutputStream;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		//Whether file has been selected or not
		System.out.println("validate method call agide");
		if(product.getFile() == null || 
				product.getFile().getOriginalFilename().equals("")){
			errors.rejectValue("file", null, "Please select image file to upload");
			
			System.out.println("if loop olag bandide");
			return;
		}
		if(!(product.getFile().getContentType().equals("image/jpeg") ||
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/gif"))){
			System.out.println("image alla idu bere eno file");
			errors.rejectValue("file", null, "Please use only image file for upload");
			return;
		}
		
		
	}

}
