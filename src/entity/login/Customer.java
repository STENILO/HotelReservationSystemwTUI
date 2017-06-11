package entity.login;

public class Customer {

	private String phonenumber="";
	private String name="";

	public Customer (String  name, String phonenumber) {
		this.name=name; 
		this.phonenumber=phonenumber; 
	}

	public String getPhonenumber() {return phonenumber;}
	public String getName() {return name;}
}