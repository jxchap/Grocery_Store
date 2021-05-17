package com.grocery.service;

import java.util.List;

import com.grocery.domain.Question;

public interface QuestionService {
	
	public Question save(Question question);
	
	public List<Question> getCustomerQuestions(String username);
	
	public List<Question> employeeGetMyQuestionsAndUnassignedQuestions(String status, String assignedTo);
	
	public Question getQuestion(String username, long questionId);

	public Question getQuestionById(long questionId);
}
