package com.hexaware.exception;          // task 7.2 employeeeid exception

public class InvalidEmployeeIdException extends Exception  {
	 public InvalidEmployeeIdException(String message) {
	        super("invalid employee id");
	        System.out.println(message);
	    }
}
