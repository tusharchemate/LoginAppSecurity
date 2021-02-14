package com.tush;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	
	public static void main(String args[]) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String pass = "Tushar";
		
		String encodedPass = encoder.encode(pass);

		System.out.println(encodedPass);
	}
}
