package com.grocery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartitem")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartItemId;

	private long foodItemId;

	public long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public long getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(int foodItemId) {
		this.foodItemId = foodItemId;
	}

	public CartItem(long foodItemId) {
		super();
		this.foodItemId = foodItemId;
	}
	
	public CartItem() {
		
	}

	
	
}
