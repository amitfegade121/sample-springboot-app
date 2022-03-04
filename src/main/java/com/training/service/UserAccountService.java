package com.training.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.training.model.User;

public class UserAccountService {
	
	private List<User> users;
	
	public UserAccountService() {
		
		users = Arrays.asList(new User("alex.browning@gmail.com", "alex@123"),
				                       new User("anna.parker@gmail.com", "anna@123"),
				                       new User("bryan.bash@gmail.com", "bryan@123"));		
	}
	
	public boolean authenticate(String email, String password) {
		
		 if (StringUtils.isBlank(email) || StringUtils.isBlank(password))
			 throw new IllegalArgumentException("Email Id or password should not be empty.");
		 
		 for (User user: users) {
			 if (user.getEmail().equals(email) && user.getPassword().equals(password))
				 return true;
		 }
		 
		 return false;
	}
	
	public boolean changePassword(String email, String oldPassword, String newPassword) {
		
		if (StringUtils.isBlank(email) || StringUtils.isEmpty(oldPassword) || StringUtils.isBlank(newPassword))
			throw new IllegalArgumentException("Email, old password or new password should not be empty.");
		
		for (User user: users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(oldPassword)) {
				if (! oldPassword.equals(newPassword)) {
					user.setPassword(newPassword);
					return true;
				}
				return false;
			}
		}		
		return false;
	}
	
	public boolean resetPasswordLink(String email) {
		
		if (StringUtils.isBlank(email))
			throw new IllegalArgumentException("Email Id should not be empty.");
		
		return users.stream()
							.anyMatch(user -> user.getEmail().equals(email));
	}
}













