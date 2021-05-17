package com.grocery.domain;

import java.awt.image.BufferedImage;

import javax.servlet.annotation.MultipartConfig;


@MultipartConfig
public class NewOrModifiedProduct {
	
	private String productname;
	private BufferedImage productimage;
	private String category;
	private String description;
	private double price;
	private int shelflife;
	private int batchsize;
	private int reorderquantity;
	private String status;
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public BufferedImage getProductimage() {
		return productimage;
	}
	public void setProductimage(BufferedImage productimage) {
		this.productimage = productimage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getShelflife() {
		return shelflife;
	}
	public void setShelflife(int shelflife) {
		this.shelflife = shelflife;
	}
	public int getBatchsize() {
		return batchsize;
	}
	public void setBatchsize(int batchsize) {
		this.batchsize = batchsize;
	}
	public int getReorderquantity() {
		return reorderquantity;
	}
	public void setReorderquantity(int reorderquantity) {
		this.reorderquantity = reorderquantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "NewOrModifiedProduct [productname=" + productname + ", productimage=" + productimage + ", category="
				+ category + ", description=" + description + ", price=" + price + ", shelflife=" + shelflife
				+ ", batchsize=" + batchsize + ", reorderquantity=" + reorderquantity + ", status=" + status + "]";
	}
	
	

}
