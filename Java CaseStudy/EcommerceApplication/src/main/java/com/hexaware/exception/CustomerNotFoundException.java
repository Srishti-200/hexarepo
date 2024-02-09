
package com.hexaware.exception;
/**
 * Throws when customer id not found
 */
public class CustomerNotFoundException extends Exception {
	/**
	 * @param message
	 */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
