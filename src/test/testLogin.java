package test;
import static org.junit.Assert.*;
import logic.LoginLogic;

import org.junit.Test;

import entity.login.Employee;

public class testLogin {

	@Test
	public void positiveLoginTest() {
		LoginLogic ll = new LoginLogic();
		ll.addEmployee(true, "Admin", "pass");
		ll.addEmployee(true, "John", "1337");
		ll.addEmployee(true, "Smith", "code");
		ll.addEmployee(true, "Charles", "MorningBreaker");
		ll.addEmployee(true, "", "pass");
		assertEquals(true, ll.validateLogin("Admin", "pass"));
		assertEquals(true, ll.validateLogin("Smith", "code"));
		assertEquals(true, ll.validateLogin("Charles", "MorningBreaker"));
		assertEquals(true, ll.validateLogin("", "pass"));
		assertEquals(true, ll.validateLogin("Admin", "pass"));
		
		
		
	}

	@Test
	public void negativeLoginTest() {
		LoginLogic ll = new LoginLogic();
		ll.addEmployee(true, "Admin", "password");
		ll.addEmployee(true, "John", "1337");
		ll.addEmployee(true, "Charles", "MorningBreaker");
		assertEquals(true, ll.validateLogin("Admin", "passwort"));
		assertEquals(true, ll.validateLogin("MorningBreaker", "Charles"));
		assertEquals(true, ll.validateLogin("", "1337"));
		
	}
	

}