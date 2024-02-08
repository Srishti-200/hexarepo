package com.hexaware.exception;            // task 7.1 trackingnumber not found

public class TrackingNumberNotFoundException  extends Exception {
	public TrackingNumberNotFoundException(String message) {
        super("inavalid tracking number");
        System.out.println(message);
    }
}
