package com.grocery;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {

	public static void main(String[] args) {
		String password = "jackjack";
		System.out.println(testBCryptHash(password));
	}

	public static String testBCryptHash(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String newPassword = passwordEncoder.encode(password);
		return newPassword;

	}

}