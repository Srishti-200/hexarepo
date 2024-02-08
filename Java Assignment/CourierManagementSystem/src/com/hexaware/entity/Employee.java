package com.hexaware.entity;                          // 5.3: POJO

public class Employee {
	 private int employeeID; 
	 private  String  employeeName;
	 private String  email ;
	 private  String  contactNumber ; 
	 private  String  role ;
	 private double salary ;
	 public Employee() {
	    }
	    public Employee(int employeeID, String employeeName, String email, String contactNumber, String role, double salary) {
	        this.employeeID = employeeID;
	        this.employeeName = employeeName;
	        this.email = email;
	        this.contactNumber = contactNumber;
	        this.role = role;
	        this.salary = salary;
	    }
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", role=" + role + ", salary=" + salary + "]";
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public boolean authenticate(int employeeId, String password) {

		return false;
	}
}
