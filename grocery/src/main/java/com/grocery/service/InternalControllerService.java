package com.grocery.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.grocery.domain.Customer;

public interface InternalControllerService {

	public JsonNode updateDatabaseCart(@RequestBody JsonNode json);

	public JsonNode buildAuthenticationContext();

	public JsonNode getCart();

	public JsonNode addNewAccount(@RequestBody Customer customer);

	public JsonNode getCustomerTransactions();

	public JsonNode submitNewQuestion(JsonNode json);

	public JsonNode addNewCommentToQuestion(JsonNode json);

	public JsonNode customerGetMyQuestions();

	public JsonNode employeeGetQuestions();
	
	public JsonNode employeeAddNewCommentToQuestion(JsonNode json);
	
	public JsonNode employeeAssignToMe(JsonNode json);
	
	public JsonNode adminGetListOfEmployees();
	
	public JsonNode adminAssignToEmployee(JsonNode json);
	
	public JsonNode sendResetPasswordToken(JsonNode json);
	
	public JsonNode resetPassword(JsonNode json);
}
