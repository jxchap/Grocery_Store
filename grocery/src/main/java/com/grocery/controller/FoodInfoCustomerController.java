package com.grocery.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grocery.domain.Customer;
import com.grocery.domain.Transaction;
import com.grocery.domain.TransactionContents;
import com.grocery.restclient.FoodInfoCustomerClient;
import com.grocery.service.CartItemService;
import com.grocery.service.CustomerService;
import com.grocery.service.EmailAndPDFService;
import com.grocery.service.TransactionService;
import com.itextpdf.text.DocumentException;

@RestController
public class FoodInfoCustomerController {
	
	@Autowired
	CartItemService cartItemService;

	@Autowired
	FoodInfoCustomerClient foodInfoCustomerClient;

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmailAndPDFService emailAndPDFService;
	

	@PostMapping(value = "/searchFoodItems")
	public ResponseEntity<JsonNode> searchFoodItems(@RequestBody JsonNode json) {
		JsonNode response = foodInfoCustomerClient.searchFoodItems(json);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/purchaseCart")
	public ResponseEntity<JsonNode> purchaseCart(@RequestBody JsonNode json) throws MessagingException, MalformedURLException, DocumentException, IOException {

		// handle food inventory deduction
		JsonNode reducedCartArray = json.get("reducedCartArray");
		var foodInfoResponse = foodInfoCustomerClient.acceptFoodOrder(reducedCartArray);
		if (!foodInfoResponse.get("message").asText().equalsIgnoreCase("Accepted")) {
			return new ResponseEntity<>(createJsonNodeWithMessage("rejected"), HttpStatus.OK);
		}

		// submit transaction to transaction table, add each line item from reduced cart
		Transaction transaction = new Transaction();
		var contextName = SecurityContextHolder.getContext().getAuthentication().getName();
		Customer customer = customerService.findByUsername(contextName);

		LocalDate localDate = LocalDate.now();
		String deliveryAddress = json.get("address").asText();
		String deliveryCity = json.get("city").asText();
		String deliveryState = json.get("state").asText();
		String deliveryZipcode = json.get("zip").asText();
		
		transaction.setFirstName(customer.getFirstname());
		transaction.setLastName(customer.getLastname());
		transaction.setMobileNum(customer.getMobileNum());
		transaction.setEmail(customer.getEmail());
		transaction.setUsername(contextName);
		transaction.setTransactionDate(localDate);
		transaction.setDeliveryAddress(deliveryAddress);
		transaction.setDeliveryCity(deliveryCity);
		transaction.setDeliveryState(deliveryState);
		transaction.setDeliveryZipcode(deliveryZipcode);

		var transactionContents = transaction.getTransactionContents();
		var foodItemIterator = reducedCartArray.iterator();

		double transactionContentsTotal = 0;
		while (foodItemIterator.hasNext()) {

			var tempObject = foodItemIterator.next();

			var foodItemId = tempObject.get("foodItemId").asLong();
			var foodItemName = tempObject.get("foodItemName").asText();
			var foodItemDescription = tempObject.get("description").asText();
			var quantity = tempObject.get("quantity").asInt();
			var costPerItem = tempObject.get("cost").asDouble() / quantity;
			// var discountPerItem = tempObject.get("quantity").asDouble();
			var totalTax = quantity * costPerItem * 0.0825;
			var totalAmount = tempObject.get("cost").asDouble() + totalTax;
			
			TransactionContents transactionLineItem =  new TransactionContents();
			transactionLineItem.setFoodItemId(foodItemId);
			transactionLineItem.setFoodItemName(foodItemName);
			transactionLineItem.setFoodItemDescription(foodItemDescription);
			transactionLineItem.setQuantity(quantity);
			transactionLineItem.setCostPerItem(costPerItem);
			//transactionLineItem.setDiscountPerItem(discountPerItem);
			transactionLineItem.setTotalTax(totalTax);
			transactionLineItem.setTotalAmount(totalAmount);
			
			transactionContents.add(transactionLineItem);

			transactionContentsTotal += quantity * costPerItem;
		}
		
		if(transactionContentsTotal < 20) {
			transaction.setDeliveryCharge(5);
		}
		
		transactionService.save(transaction);
		


		// send email to customer with receipt of purchase
		String tempDoc = emailAndPDFService.saveTransactionToPDF(transaction);
		emailAndPDFService.sendMessageWithAttachment(transaction.getEmail(),
				"[DO NOT REPLY] Receipt for your food delivery",
				"Please refer to pdf attachment for transaction details.", tempDoc);
		
		var oldCart = customer.getCartItems();
		customerService.save(customer);
		
		oldCart.stream().map((e) -> e.getCartItemId()).forEach((e) -> cartItemService.deleteById(e));
		

		return new ResponseEntity<>(createJsonNodeWithMessage("Order Submitted"),HttpStatus.OK);

	}
	
	@GetMapping(value = "/getRandomBestActiveOffers")
	public ResponseEntity<JsonNode> getRandomBestActiveOffers() {
		JsonNode response = foodInfoCustomerClient.getRandomBestActiveOffers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// utility method
	private JsonNode createJsonNodeWithMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("message", message);
		var response = mapper.convertValue(objectNode, JsonNode.class);
		return response;

	}

}
