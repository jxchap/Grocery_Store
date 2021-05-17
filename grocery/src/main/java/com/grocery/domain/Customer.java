package com.grocery.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileNum;
	private String area;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	
	private String resetToken;
	private LocalDateTime resetTokenTimeStamp;

	private LocalDateTime cartLastSaved;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItem = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_role")
	private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<CartItem> getCartItems() {
		return cartItem;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItem = cartItems;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCartLastSaved() {
		return cartLastSaved;
	}

	public void setCartLastSaved(LocalDateTime cartLastSaved) {
		this.cartLastSaved = cartLastSaved;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public LocalDateTime getResetTokenTimeStamp() {
		return resetTokenTimeStamp;
	}

	public void setResetTokenTimeStamp(LocalDateTime resetTokenTimeStamp) {
		this.resetTokenTimeStamp = resetTokenTimeStamp;
	}

}
