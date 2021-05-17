package com.grocery.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grocery.restclient.FoodInfoAdminClient;

@RestController
public class FoodInfoAdminController {

	@Autowired
	FoodInfoAdminClient foodInfoAdminClient;

	@PostMapping(value = "/admin/submitNewOffer")
	public ResponseEntity<JsonNode> submitNewOffer(@RequestBody JsonNode json) {
		JsonNode response = foodInfoAdminClient.submitNewOffer(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/admin/deleteOffer")
	public ResponseEntity<JsonNode> deleteOffer(@RequestBody JsonNode json) {
		JsonNode response = foodInfoAdminClient.deleteOffer(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/admin/getOffersForCategoryAdmin")
	public ResponseEntity<JsonNode> getOffersForCategoryAdmin(@RequestBody JsonNode json) {
		JsonNode response = foodInfoAdminClient.getOffersForCategoryAdmin(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/admin/searchFoodItemsByCategory")
	public ResponseEntity<JsonNode> searchFoodItemsByCategory(@RequestBody JsonNode json) {
		JsonNode response = foodInfoAdminClient.searchFoodItemsByCategory(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/admin/submitModifiedProduct")
	public ResponseEntity<JsonNode> submitModifiedProduct(@RequestBody JsonNode json) {
		JsonNode response = foodInfoAdminClient.submitModifiedProduct(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/admin/submitNewProduct")
	public String submitNewProduct(@RequestParam("productName") String productName,
			@RequestParam("productImage") MultipartFile file, 
			@RequestParam("category") String category,
			@RequestParam("description") String description, 
			@RequestParam("price") double price,
			@RequestParam("shelfLife") int shelfLife, 
			@RequestParam("batchSize") int batchSize,
			@RequestParam("reorderQuantity") int reorderQuantity, 
			@RequestParam("productStatusGroup") String status) {
		
		ObjectNode newProduct = new ObjectMapper().createObjectNode();
		newProduct.put("foodItemName", productName);
		newProduct.put("description", description);
		newProduct.put("cost", price);
		newProduct.put("shelfLife", shelfLife);
		newProduct.put("batchSize", batchSize);
		newProduct.put("status", status);
		newProduct.put("reorderPoint", reorderQuantity);
		newProduct.put("foodCategory", category);

		JsonNode toFoodInfo = new ObjectMapper().convertValue(newProduct, JsonNode.class);

		JsonNode response = foodInfoAdminClient.submitNewProduct(toFoodInfo);

		byte[] bytes = new byte[(int) file.getSize()];
		try {
			bytes = file.getBytes();
			
				Path myPath = Paths.get("src/main/resources/static/images/", response.get("foodItemId").asText() + ".jpg");
				Files.write(myPath, bytes);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		return "admin/products";
	}

	@GetMapping(value = "/admin/getPurchasesAdmin")
	public ResponseEntity<JsonNode> getPurchases() {
		JsonNode response = foodInfoAdminClient.getPurchasesAdmin();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/admin/confirmPurchaseAdmin")
	public ResponseEntity<JsonNode> confirmPurchaseAdmin(@RequestBody JsonNode json) {
		JsonNode response = foodInfoAdminClient.confirmPurchaseAdmin(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}