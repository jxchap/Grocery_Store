package com.grocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.grocery.restclient.FoodInfoUniversalClient;
 
@RestController
public class FoodInfoUniversalController {
	
	@Autowired
	FoodInfoUniversalClient foodInfoClient;
	
	@GetMapping(value = "/getFoodCategories")
	public ResponseEntity<JsonNode> getFoodCategories() {
		JsonNode response = foodInfoClient.getFoodCategories();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	


}