package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grocery.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Transactional
	void deleteByCartItemId(long cartItemId);

}
