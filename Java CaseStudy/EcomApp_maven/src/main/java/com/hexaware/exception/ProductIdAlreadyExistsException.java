/**
 * Exception package to handle exceptions
 */
package com.hexaware.exception;

public class ProductIdAlreadyExistsException extends Exception {
	/**
	 * throws exception when the product id entered already exists
	 * @param message
	 */
    public ProductIdAlreadyExistsException (String message) {
        super(message); 
    }
}
