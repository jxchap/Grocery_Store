package com.grocery.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class FoodInfoCustomerClient {
	
	@Autowired
	RestClientUtilities restClientUtilities;

	private static final String foodInfoMicroservice = "http://localhost:9393/";

	public JsonNode searchFoodItems(JsonNode node) {

		String externalEndpoint = foodInfoMicroservice + "/searchFoodItems";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(node, externalEndpoint);

		return responseObject;
	}

	public JsonNode getFoodItemObjectsForCart(JsonNode node) {

		String externalEndpoint = foodInfoMicroservice + "/getFoodItemObjectsForCart";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(node, externalEndpoint);

		return responseObject;
	}
	
	public JsonNode acceptFoodOrder(JsonNode node) {

		String externalEndpoint = foodInfoMicroservice + "/acceptFoodOrder";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(node, externalEndpoint);

		return responseObject;
	}
	
	public JsonNode getRandomBestActiveOffers() {

		String externalEndpoint = foodInfoMicroservice + "/getRandomBestActiveOffers";
		JsonNode responseObject = restClientUtilities.retrieveGetResponseObject(externalEndpoint);

		return responseObject;
	}

	

}
