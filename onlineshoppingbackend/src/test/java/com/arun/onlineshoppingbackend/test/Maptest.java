package com.arun.onlineshoppingbackend.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Maptest {
	
	 public static void main(String [] args) {
	 
	 Map<String,String> value = new HashMap<>();
	 value.put("Name", "Rajue");
	 value.put("Name", "Raju1");
	 value.put("Country", "India");
	 value.put(null, null);
	 value.put("Name", "Rajesh");
	 value.put(null, "dwqfdhfww");
	 value.put("Null Value", null);
	        System.out.println(value);
	        // To fetch a value, we use get method and pass the key as parameter
	 System.out.println("Value of Name is : "+value.get("Name"));
	 // If we pass a key which is not available - You wont face any exception, output will be null
	 System.out.println("Value of Name is : "+value.get("Name1"));
	 // To get the size of the Map
	 System.out.println("Size is : "+value.size());
	 
	 
	 /*As a Map is not a true collection, there is no direct method for iterating over a map. 
	 Instead, we can iterate over a map using its collection views. 
	 Any Map’s implementation has to provide three Collection view methods ie., keySet(), values(), and entrySet()*/
	 
	 //Converting to Set so that we can transverse
	 Set<String> keys = value.keySet();
	 
	 for(String key:keys) {
	 System.out.println(key + " = " + value.get(key));
	 }
	 // To get the value
	 System.out.println("Value of Country is : "+value.get("Country"));
	 // To remove the value
	 value.remove("Country");
	 // Lets see how the value will be after we applied remove in the above statement
	 System.out.println("Value of Country after removed it is : "+value.get("Country"));
	 
	 
	 } 
	}


