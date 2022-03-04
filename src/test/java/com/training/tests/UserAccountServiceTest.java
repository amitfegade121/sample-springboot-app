package com.training.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.training.service.UserAccountService;

@DisplayName("Testing UserAccountService class")
public class UserAccountServiceTest {

	private UserAccountService service;

	@BeforeEach
	public void setUp() {
		service = new UserAccountService();
	}

	@DisplayName("Test Login")
	@Nested
	class AuthenticateTest {

		@Test
		public void authenticate_Should_Return_True_When_CredentialsAreCorrect() {

			assertTrue(service.authenticate("alex.browning@gmail.com", "alex@123"));
		}

		@Test
		public void authenticate_Should_Retuen_False_When_CredentialsAreIncorrect() {

			assertFalse(service.authenticate("anna.parker@gmail.com", "password1234"));
		}

		@Test
		public void authenticate_Should_ThrowAnException_When_EmptyEmailOrPasswordIsProvided() {

			assertThrows(IllegalArgumentException.class, () -> service.authenticate("", "password123"));
		}
	}
	
	@DisplayName("Test Reset Password Link")
	@Nested
	class ResetPasswordLinkTest {
		
		@Test
		public void resetPasswordLink_Should_Return_True_WhenEmailIsAlreadyRegistered() {
			assertTrue(service.resetPasswordLink("bryan.bash@gmail.com"));
		}
		
		@Test
		public void resetPasswordLink_Should_Return_False_WhenEmailIsNotRegistered() {
			
			assertFalse(service.resetPasswordLink("george.king@gmail.com"));
		}
		
		@Test
		public void resetPasswordLink_Should_Throw_Exception_WhenEmptyEmailIsProvided() {
			
			assertThrows(IllegalArgumentException.class, () -> service.resetPasswordLink(""));
		}
	}
	
	@DisplayName("Test Change Password")
	@Nested
	class ChangePasswordTest {
		
		@Test
		public void changePassword_Should_Return_True_WhenCredentialsAreCorrect_And_OldPasswordAndNewPasswordAreNotSame() {
				
			assertTrue(service.changePassword("anna.parker@gmail.com", "anna@123", "password@123"));
		}
		
		@Test
		public void changePassword_Should_Return_False_WhenCredentialsAreIncorrect() {
				
			assertFalse(service.changePassword("alex.browning@gmail.com", "alex@12345", "password@123"));
		}
		
		@Test
		public void changePassword_Should_Return_False_WhenCredentialsAreCorrect_And_OldPAsswordAndNewPasswordAreSame() {
				
			assertFalse(service.changePassword("alex.browning@gmail.com", "alex@123", "alex@123"));
		}
		
		@Test
		public void changePassword_Should_Throw_Exception_WhenEmailOrPasswordOrNewPasswordIsEmpty() {
			
			assertThrows(IllegalArgumentException.class, () -> service.changePassword("", "", "password@123"));
		}
	}

	@AfterEach
	public void teadDown() {
		service = null;
	}
}




















