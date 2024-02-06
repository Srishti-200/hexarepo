package com.hexaware.util;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DBUtil {

		static Connection con;
		
		public static Connection getDBConn() {
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test4", "root", "Rsss@2025");
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return con;
		}
				}

