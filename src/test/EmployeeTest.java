package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.login.Employee;

public class EmployeeTest {

	@Test
	public void positiveNameTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setUsername("Controller");
		emp.setUsername("SysAdmin");
		assertEquals("SysAdmin", emp.getUsername());
		
	}
	
	@Test
	public void negativeNameTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setUsername("NOtAdmin");
		emp.setUsername("SysAdmin");
		assertEquals("NotAdmin", emp.getUsername());
		
	}
	
	@Test
	public void positiveActiveTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setActive(false);
		assertEquals(false, emp.isActive());
		
	}
	
	@Test
	public void negativeActiveTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setActive(false);
		assertEquals(true, emp.isActive());
		
	}
	
	@Test
	public void positiveAdminTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setAdmin(false);
		assertEquals(false, emp.isAdmin());
		
	}
	
	@Test
	public void negativeAdminTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setAdmin(false);
		assertEquals(true, emp.isAdmin());
		
	}
	
	@Test
	public void positivePasswordTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setPassword("password");
		assertEquals("password", emp.getPassword());
		
	}
	
	@Test
	public void negativePasswordTest() {
		Employee emp = new Employee(true, true, "Admin", "pass");
		emp.setPassword("passwort");
		assertEquals("password", emp.getPassword());
		
	}

}
