package com.grocery.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.repository.CartItemRepository;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public void deleteById(long cartItemId) {
		cartItemRepository.deleteByCartItemId(cartItemId);
	}

}
