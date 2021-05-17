package com.grocery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	List<Question> findByCustomerUsername(String username);
	
	Optional<Question> findByCustomerUsernameAndQuestionId(String username, long questionId);
	
	List<Question> findByStatusOrAssignedTo(String status, String assignedTo);

}
