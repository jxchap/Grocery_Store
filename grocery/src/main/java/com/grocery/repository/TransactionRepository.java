package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByUsername(String username);
}
