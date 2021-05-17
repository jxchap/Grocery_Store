package com.grocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.domain.Question;
import com.grocery.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public Question save(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public List<Question> getCustomerQuestions(String username) {
		return questionRepository.findByCustomerUsername(username);
	}

	@Override
	public Question getQuestion(String username, long questionId) {

		var result = questionRepository.findByCustomerUsernameAndQuestionId(username, questionId);

		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<Question> employeeGetMyQuestionsAndUnassignedQuestions(String status, String assignedTo) {

		return questionRepository.findByStatusOrAssignedTo(status, assignedTo);
	}

	@Override
	public Question getQuestionById(long questionId) {
		var result = questionRepository.findById(questionId);
		
		return result.isPresent() ? result.get() : null;
	}

}
