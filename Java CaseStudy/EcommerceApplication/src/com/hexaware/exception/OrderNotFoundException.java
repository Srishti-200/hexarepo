
package com.hexaware.exception;
/**
 * Throws exception when Order Id is not found
 */
public class OrderNotFoundException extends Exception {
	/**
	 * @param message
	 */
    public OrderNotFoundException(String message) {
        super(message);
    }
}
