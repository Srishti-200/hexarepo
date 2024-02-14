
package com.hexaware.exception;
/**
 * To throw when product id is not found to search something in cart
 */
public class ProductNotFoundException extends Exception {
	/**
	 * @param message
	 */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
