package com.grocery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactioncontents")
public class TransactionContents {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long transactionContentsId;
	
	private long foodItemId;
	private String foodItemName;
	private String foodItemDescription;
	private int quantity;
	private double costPerItem;
	private double discountPerItem;
	private double totalAmount;
	private double totalTax;
	
	public long getTransactionContentsId() {
		return transactionContentsId;
	}
	public void setTransactionContentsId(long transactionContentsId) {
		this.transactionContentsId = transactionContentsId;
	}
	public long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(long foodItemId) {
		this.foodItemId = foodItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCostPerItem() {
		return costPerItem;
	}
	public void setCostPerItem(double costPerItem) {
		this.costPerItem = costPerItem;
	}
	public double getDiscountPerItem() {
		return discountPerItem;
	}
	public void setDiscountPerItem(double discountPerItem) {
		this.discountPerItem = discountPerItem;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public String getFoodItemName() {
		return foodItemName;
	}
	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}
	public String getFoodItemDescription() {
		return foodItemDescription;
	}
	public void setFoodItemDescription(String foodItemDescription) {
		this.foodItemDescription = foodItemDescription;
	}
	
	

	
}



