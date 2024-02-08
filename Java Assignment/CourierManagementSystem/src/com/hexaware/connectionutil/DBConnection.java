package com.hexaware.connectionutil;           // Task9.1 DBConnection
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DBConnection {

		static Connection con;
	                                                                                                                                     	   // Task 9.1  Database connection
		public static Connection getDBConn() {
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sri", "root", "Rsss@2025");
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return con;
		}
		
		
		public static void main(String[] args) {
			System.out.println(getDBConn());

		}

}

