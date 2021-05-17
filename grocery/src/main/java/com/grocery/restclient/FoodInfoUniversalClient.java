package com.grocery.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class FoodInfoUniversalClient {
	
	@Autowired
	RestClientUtilities restClientUtilities;

	private static final String foodInfoMicroservice = "http://localhost:9393/";

	public JsonNode getFoodCategories() {

		String externalEndpoint = foodInfoMicroservice + "/getFoodCategories";
		JsonNode responseObject = restClientUtilities.retrieveGetResponseObject(externalEndpoint);

		return responseObject;
	}



}
