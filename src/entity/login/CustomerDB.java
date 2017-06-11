package entity.login;

import java.util.ArrayList;

import entity.login.Customer;

public class CustomerDB {

	private static ArrayList<Customer> customers;

	public CustomerDB() {
		customers=new ArrayList<Customer>();
	}

	public void addCustomer (String name,  String phonenumber) {
		customers.add(new Customer(name, phonenumber));
	}
	public Customer getCustomer(String phonenumber){
		for (Customer c:customers) {
			if(c.getPhonenumber().equals(phonenumber)) return c;
		}
		return new Customer("","");
	}
}