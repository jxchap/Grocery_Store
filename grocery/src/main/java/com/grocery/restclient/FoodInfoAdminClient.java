package com.grocery.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class FoodInfoAdminClient {

	@Autowired
	RestClientUtilities restClientUtilities;

	private static final String foodInfoMicroservice = "http://localhost:9393/";

	public JsonNode searchFoodItemsByCategory(JsonNode node) {

		String externalEndpoint = foodInfoMicroservice + "/searchFoodItemsByCategory";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(node, externalEndpoint);

		return responseObject;
	}

	public JsonNode deleteOffer(JsonNode node) {

		String externalEndpoint = foodInfoMicroservice + "/deleteOffer";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(node, externalEndpoint);

		return responseObject;
	}

	public JsonNode submitNewOffer(JsonNode node) {

		String externalEndpoint = foodInfoMicroservice + "/submitNewOffer";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(node, externalEndpoint);

		return responseObject;
	}

	public JsonNode getOffersForCategoryAdmin(JsonNode json) {

		String externalEndpoint = foodInfoMicroservice + "/getOffersForCategoryAdmin";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(json, externalEndpoint);

		return responseObject;
	}
	
	public JsonNode submitModifiedProduct(JsonNode json) {

		String externalEndpoint = foodInfoMicroservice + "/submitModifiedProduct";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(json, externalEndpoint);

		return responseObject;
	}
	
	public JsonNode submitNewProduct(JsonNode json) {

		String externalEndpoint = foodInfoMicroservice + "/submitNewProduct";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(json, externalEndpoint);

		return responseObject;
	}
	
	public JsonNode getPurchasesAdmin() {

		String externalEndpoint = foodInfoMicroservice + "/getPurchasesAdmin";
		JsonNode responseObject = restClientUtilities.retrieveGetResponseObject(externalEndpoint);

		return responseObject;
	}
	
	public JsonNode confirmPurchaseAdmin(JsonNode json) {

		String externalEndpoint = foodInfoMicroservice + "/confirmPurchaseAdmin";
		JsonNode responseObject = restClientUtilities.retrievePostResponseObject(json, externalEndpoint);

		return responseObject;
	}

}
