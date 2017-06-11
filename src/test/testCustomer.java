package test;
import static org.junit.Assert.*;
import entity.login.CustomerDB;
import org.junit.Test;

public class testCustomer {

	@Test
	public void positiveCustomerTest() {
		CustomerDB customer = new  CustomerDB();
		customer.addCustomer("Hans Hansen", "12345678");
		customer.addCustomer("Jens Jensen", "12345679");
		customer.addCustomer("Hans Hansen", "87654321");
		customer.addCustomer("Bill Jobs", "10000000");
		assertEquals("Hans Hansen", customer.getCustomer("12345678").getName());
		assertEquals("Jens Jensen", customer.getCustomer("12345679").getName());
		assertEquals("Hans Hansen", customer.getCustomer("87654321").getName());
		assertEquals("Bill Jobs", customer.getCustomer("10000000").getName());
		
	
	}

	
	@Test
	public void negativeCustomerTest() {
		CustomerDB customer = new  CustomerDB();
		customer.addCustomer("John Johnson", "123123112");
		customer.addCustomer("Hans Hansen", "87654321");
		customer.addCustomer("Bill Jobs", "10000000");
		assertEquals("Hans Hansen", customer.getCustomer("12312312").getName());
		assertEquals("Jobs Bill", customer.getCustomer("").getName());
		assertEquals("Hans Hansen", customer.getCustomer("10000000").getName());
	
	}
	

	
}