package com.grocery.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grocery.PasswordHash;
import com.grocery.domain.CartItem;
import com.grocery.domain.Customer;
import com.grocery.domain.Question;
import com.grocery.domain.QuestionContents;
import com.grocery.restclient.FoodInfoCustomerClient;

@Service
public class InternalControllerServiceImpl implements InternalControllerService {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	CustomerService customerService;

	@Autowired
	RoleService roleService;

	@Autowired
	FoodInfoCustomerClient foodInfoCustomerClient;

	@Autowired
	TransactionService transactionService;

	@Autowired
	QuestionService questionService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	Z_ServiceUtilities z_ServiceUtilities;

	@Autowired
	EmailAndPDFService emailAndPDFService;

	public JsonNode updateDatabaseCart(@RequestBody JsonNode json) {

		if (!hasUserRole()) {
			return createJsonNodeWithMessage("Unable to Save Cart. Are you logged in?");
		}

		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		Customer customerForCartUpdate = customerService.findByUsername(currentUsername);

		// delete old cart items
		var oldCart = customerForCartUpdate.getCartItems();

		// submit new cart items
		Long[] tempList = new ObjectMapper().convertValue(json.get("newCart"), Long[].class);
		List<Long> newIdList = Arrays.asList(tempList);

		List<CartItem> newCart = new ArrayList<>();
		newIdList.stream().map((e) -> new CartItem(e)).forEach((e) -> newCart.add(e));
		customerForCartUpdate.setCartLastSaved(LocalDateTime.now());

		customerForCartUpdate.setCartItems(newCart);
		customerService.save(customerForCartUpdate);

		oldCart.stream().map((e) -> e.getCartItemId()).forEach((e) -> cartItemService.deleteById(e));

		return createJsonNodeWithMessage("Successfully Updated Cart");

	}

	public JsonNode buildAuthenticationContext() {

		if (!hasUserRole()) {
			return null;
		}

		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		var customerLoggedIn = customerService.findByUsername(user);

		ObjectNode response = new ObjectMapper().createObjectNode();
		response.put("firstName", customerLoggedIn.getFirstname());
		response.put("lastName", customerLoggedIn.getLastname());
		response.put("email", customerLoggedIn.getEmail());
		response.put("address", customerLoggedIn.getAddress());
		response.put("city", customerLoggedIn.getCity());
		response.put("state", customerLoggedIn.getState());
		response.put("zip", customerLoggedIn.getZipcode());
		response.put("dbCartLastSaved", customerLoggedIn.getCartLastSaved().toString());

		JsonNode responseObject = new ObjectMapper().convertValue(response, JsonNode.class);

		return responseObject;
	}

	public JsonNode getCart() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Customer customer = customerService.findByUsername(username);

		if (customer == null) {
			return createJsonNodeWithMessage("You must have a customer account to retrieve cart");
		}

		List<Long> listOfFoodItemIds = new ArrayList<>();
		customer.getCartItems().stream().map(e -> e.getFoodItemId()).forEach(e -> listOfFoodItemIds.add(e));

		ObjectMapper mapper = new ObjectMapper();
		JsonNode cartItemIdsJsonNode = mapper.convertValue(listOfFoodItemIds, JsonNode.class);

		// send Ids to foodinfo microservice to get objects
		JsonNode responseObject = foodInfoCustomerClient.getFoodItemObjectsForCart(cartItemIdsJsonNode);

		return responseObject;
	}

	public JsonNode addNewAccount(@RequestBody Customer customer) {
		System.out.println(customer.getMobileNum());

		boolean customerAlreadyExists = z_ServiceUtilities.checkForExistingUsernameOrEmail(customer.getUsername(),
				customer.getEmail());
		if (customerAlreadyExists) {
			return createJsonNodeWithMessage("Duplicate username or email detected");
		}

		customer.setPassword((PasswordHash.testBCryptHash(customer.getPassword())));
		var insertRole = customer.getRoles();
		insertRole.add(roleService.findByRoleName("ROLE_USER"));
		customer.setCartLastSaved(LocalDateTime.now().minusDays(1));
		var tempUser = customerService.save(customer);

		if (tempUser != null) {
			return createJsonNodeWithMessage("Successfully Created Account!");
		}

		return null;

	}

	@Override
	public JsonNode customerGetMyQuestions() {

		if (!hasUserRole()) {
			return null;
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		var questionList = questionService.getCustomerQuestions(username);

		for (Question question : questionList) {
			Collections.sort(question.getQuestionContents());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode questionListJson = mapper.convertValue(questionList, JsonNode.class);

		return questionListJson;
	}

	@Override
	public JsonNode employeeGetQuestions() {

		var questionList = questionService.employeeGetMyQuestionsAndUnassignedQuestions("unanswered", "unassigned");

		for (Question question : questionList) {
			Collections.sort(question.getQuestionContents());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode questionListJson = mapper.convertValue(questionList, JsonNode.class);

		return questionListJson;
	}

	@Override
	public JsonNode getCustomerTransactions() {
		if (!hasUserRole()) {
			return null;
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		var transactionList = transactionService.findByUsername(username);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode transactionListJson = mapper.convertValue(transactionList, JsonNode.class);

		return transactionListJson;

	}

	@Override
	public JsonNode submitNewQuestion(JsonNode json) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// extract values from json
		String subject = json.get("subject").asText();
		String questionContents = json.get("questionContents").asText();
		String orderId = null;
		if (json.has("orderId")) {
			orderId = json.get("orderId").asText();
		}

		// create question object and first question contents
		Question question = new Question();
		question.setAssignedTo("unassigned");
		question.setCreatedOn(LocalDate.now());
		question.setStatus("unanswered");
		question.setSubject(subject);
		question.setCustomerUsername(username);
		question.setOrderId(orderId);

		var questionContentsSet = question.getQuestionContents();

		questionContentsSet.add(new QuestionContents(username, questionContents, LocalDateTime.now()));

		questionService.save(question);

		return createJsonNodeWithMessage("Question Successfully Submitted");
	}

	@Override
	public JsonNode addNewCommentToQuestion(JsonNode json) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		var questionId = json.get("clickedQuestionId").asLong();
		var newComment = json.get("comment").asText();

		var questionToUpdate = questionService.getQuestion(username, questionId);
		var questionContentsSet = questionToUpdate.getQuestionContents();

		QuestionContents newContent = new QuestionContents(username, newComment, LocalDateTime.now());
		questionContentsSet.add(newContent);

		questionToUpdate.setStatus("unanswered");

		questionService.save(questionToUpdate);

		return createJsonNodeWithMessage("Comment Successfully Submitted");
	}

	@Override
	public JsonNode employeeAddNewCommentToQuestion(JsonNode json) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		var questionId = json.get("clickedQuestionId").asLong();
		var newComment = json.get("comment").asText();

		var questionToUpdate = questionService.getQuestionById(questionId);
		var questionContentsSet = questionToUpdate.getQuestionContents();

		QuestionContents newContent = new QuestionContents(username, newComment, LocalDateTime.now());
		questionContentsSet.add(newContent);

		questionToUpdate.setAssignedTo(username);
		questionToUpdate.setStatus("answered");

		questionService.save(questionToUpdate);

		return createJsonNodeWithMessage("Comment Successfully Submitted");
	}

	@Override
	public JsonNode employeeAssignToMe(JsonNode json) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var questionId = json.get("clickedQuestionId").asLong();
		var questionToUpdate = questionService.getQuestionById(questionId);

		questionToUpdate.setAssignedTo(username);

		questionService.save(questionToUpdate);

		return createJsonNodeWithMessage("Successfully Assigned");
	}

	@Override
	public JsonNode adminGetListOfEmployees() {

		var transactionList = employeeService.findEmployeesByRole("Employee");

		return new ObjectMapper().convertValue(transactionList, JsonNode.class);
	}

	@Override
	public JsonNode adminAssignToEmployee(JsonNode json) {

		var questionId = json.get("clickedQuestionId").asLong();
		var employeeUserNameFromJson = json.get("assignedTo").asText();

		var empDbSearchResult = employeeService.findEmployeeByUsername(employeeUserNameFromJson);

		String employeeToAssignToQuestion = empDbSearchResult != null ? empDbSearchResult.getUsername() : "unassigned";

		var questionToReassign = questionService.getQuestionById(questionId);
		questionToReassign.setAssignedTo(employeeToAssignToQuestion);
		questionService.save(questionToReassign);

		return createJsonNodeWithMessage("Successfully Reassigned");
	}

	@Override
	public JsonNode sendResetPasswordToken(JsonNode json) {

		var emailToAppendTokenTo = json.get("emailForPassReset").asText();

		var customerToUpdate = customerService.findByEmail(emailToAppendTokenTo);
		var employeeToUpdate = employeeService.findEmployeeByEmail(emailToAppendTokenTo);
		String newToken = UUID.randomUUID().toString();

		if (customerToUpdate != null) {
			customerToUpdate.setResetToken(newToken);
			customerToUpdate.setResetTokenTimeStamp(LocalDateTime.now());
			customerService.save(customerToUpdate);
			sendEmailResetToken(customerToUpdate.getEmail(), customerToUpdate.getFirstname(), newToken);

			return createJsonNodeWithMessage("Email sent with password reset token");

		} else if (employeeToUpdate != null) {
			employeeToUpdate.setResetToken(newToken);
			employeeToUpdate.setResetTokenTimeStamp(LocalDateTime.now());
			employeeService.save(employeeToUpdate);
			sendEmailResetToken(employeeToUpdate.getEmail(), employeeToUpdate.getFirstname(), newToken);

			return createJsonNodeWithMessage("Email sent with password reset token");

		} else {
			return createJsonNodeWithMessage("Email address not found");
		}

	}

	@Override
	public JsonNode resetPassword(JsonNode json) {

		String email = json.get("email").asText();
		String newPassword = json.get("newPassword").asText();
		String token = json.get("token").asText();
		LocalDateTime currentTimeMinus30 = LocalDateTime.now().minusMinutes(30);

		var customerToUpdate = customerService.findByEmail(email);
		var employeeToUpdate = employeeService.findEmployeeByEmail(email);
		
		if (customerToUpdate != null && customerToUpdate.getResetTokenTimeStamp().isAfter(currentTimeMinus30)
				&& token.equals(customerToUpdate.getResetToken())) {
			customerToUpdate.setPassword((PasswordHash.testBCryptHash(newPassword)));
			customerToUpdate.setResetToken(null);
			customerToUpdate.setResetTokenTimeStamp(null);
			customerService.save(customerToUpdate);
			
			return createJsonNodeWithMessage("Password successfully Updated");
			
		} else if (employeeToUpdate != null && employeeToUpdate.getResetTokenTimeStamp().isAfter(currentTimeMinus30)
				&& token.equals(employeeToUpdate.getResetToken())) {
			employeeToUpdate.setPassword((PasswordHash.testBCryptHash(newPassword)));
			employeeToUpdate.setResetToken(null);
			employeeToUpdate.setResetTokenTimeStamp(null);
			employeeService.save(employeeToUpdate);
			
			return createJsonNodeWithMessage("Password successfully Updated");
		}

		return null;
	}

	// utility method
	public void sendEmailResetToken(String emailingTo, String firstname, String token) {

		String subject = "Grocery Store Password Reset";
		String text = "Hello " + firstname + "," + "\n\nPlease use the provided token to reset your password. "
				+ "\n\nToken: " + token + "\n\nUse your token here: http://127.0.0.1:9292/resetpassword";

		emailAndPDFService.sendSimpleMessage(emailingTo, subject, text);

	}

	// utility method
	private boolean hasUserRole() {
		for (Object object : SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()) {
			if ("ROLE_USER".equals(object.toString())) {
				return true;
			}
		}
		return false;
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
