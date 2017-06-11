package logic;

import java.util.ArrayList;

import entity.login.Employee;

public class LoginLogic {
	private static ArrayList<Employee> employees;

	public LoginLogic(){
		employees = new ArrayList<Employee>();
	}
	public void addEmployee(boolean admin, String username, String password) {
		employees.add(new Employee(false, admin, username, password));
	}

	public boolean validateLogin(String username, String password){
		for(Employee e:employees){
			if(e.getUsername() == username && e.getPassword() == password)
				return true;
		}
		return false;
	}
}
