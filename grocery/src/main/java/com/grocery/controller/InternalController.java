package com.grocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.grocery.domain.Customer;
import com.grocery.service.InternalControllerService;

@RestController
public class InternalController {

	@Autowired
	InternalControllerService internalControllerService;

	@PostMapping(value = "/updateDatabaseCart")
	public ResponseEntity<JsonNode> updateDatabaseCart(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.updateDatabaseCart(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/getCart")
	public ResponseEntity<JsonNode> getCart() {

		JsonNode responseObject = internalControllerService.getCart();

		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}

	@PostMapping(value = "/addNewAccount")
	public ResponseEntity<JsonNode> addNewAccount(@RequestBody Customer customer) {

		JsonNode response = internalControllerService.addNewAccount(customer);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/submitNewQuestion")
	public ResponseEntity<JsonNode> submitNewQuestion(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.submitNewQuestion(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/addNewCommentToQuestion")
	public ResponseEntity<JsonNode> addNewCommentToQuestion(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.addNewCommentToQuestion(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/employeeAddNewCommentToQuestion")
	public ResponseEntity<JsonNode> employeeAddNewCommentToQuestion(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.employeeAddNewCommentToQuestion(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/employeeAssignToMe")
	public ResponseEntity<JsonNode> employeeAssignToMe(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.employeeAssignToMe(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/adminAssignToEmployee")
	public ResponseEntity<JsonNode> adminAssignToEmployee(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.adminAssignToEmployee(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	
	@GetMapping(value = "/customerGetMyQuestions")
	public ResponseEntity<JsonNode> customerGetMyQuestions() {

		JsonNode responseObject = internalControllerService.customerGetMyQuestions();

		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employeeGetQuestions")
	public ResponseEntity<JsonNode> employeeGetQuestions() {

		JsonNode responseObject = internalControllerService.employeeGetQuestions();

		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}

	@GetMapping(value = "/buildAuthenticationContext")
	public ResponseEntity<JsonNode> buildAuthenticationContext() {

		JsonNode responseObject = internalControllerService.buildAuthenticationContext();

		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCustomerTransactions")
	public ResponseEntity<JsonNode> getCustomerTransactions() {

		JsonNode response = internalControllerService.getCustomerTransactions();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping(value = "/adminGetListOfEmployees")
	public ResponseEntity<JsonNode> adminGetListOfEmployees() {

		JsonNode response = internalControllerService.adminGetListOfEmployees();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/sendResetPasswordToken")
	public ResponseEntity<JsonNode> sendResetPasswordToken(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.sendResetPasswordToken(json);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/resetPassword")
	public ResponseEntity<JsonNode> resetPassword(@RequestBody JsonNode json) {

		JsonNode response = internalControllerService.resetPassword(json);

		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}


	@PostMapping(value = "/doSomething")
	public ResponseEntity<JsonNode> doSomething(@RequestBody JsonNode json) {
		

		
		return null;

	}

}