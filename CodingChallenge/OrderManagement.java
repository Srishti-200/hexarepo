package com.hexaware.main;

import java.sql.SQLException;
import com.hexaware.controller.OrderProcessor;

public class OrderManagement {

	public static void main(String[] args) throws SQLException {
		System.out.println("***********************************WELCOME TO ORDER MANAGEMENT***********************************************");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
	    OrderProcessor.startApp();
	}
 
}
