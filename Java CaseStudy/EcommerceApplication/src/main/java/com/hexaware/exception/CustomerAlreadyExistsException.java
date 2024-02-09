package com.hexaware.exception;

/**
 * Exception thrown when attempting to add a customer with an already existing id.
 */
public class CustomerAlreadyExistsException  extends Exception{
	/**
	 * @param message
	 */
	public  CustomerAlreadyExistsException   (String message) {
        super(message);
	}
}
