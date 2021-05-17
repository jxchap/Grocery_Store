package com.grocery.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questioncontents")
public class QuestionContents implements Comparable<QuestionContents>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long questionContentsId;

	private String username;
	private String message;
	private LocalDateTime messageDate;

	public long getQuestionContentsId() {
		return questionContentsId;
	}

	public void setQuestionContentsId(long questionContentsId) {
		this.questionContentsId = questionContentsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDateTime messageDate) {
		this.messageDate = messageDate;
	}

	public QuestionContents(String username, String message, LocalDateTime messageDate) {
		super();
		this.username = username;
		this.message = message;
		this.messageDate = messageDate;
	}

	public QuestionContents() {

	}

	@Override
	public int compareTo(QuestionContents o) {
		return o.messageDate.compareTo(this.messageDate);
	}
	
	

}
