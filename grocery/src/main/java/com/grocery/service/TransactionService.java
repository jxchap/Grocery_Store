package com.grocery.service;

import java.util.List;

import com.grocery.domain.Transaction;

public interface TransactionService {
	
	public Transaction save(Transaction transaction);
	
	List<Transaction> findByUsername(String username);
	
}
