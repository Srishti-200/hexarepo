
package com.hexaware.util;
/**
 * Database Connection Done
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

	static Connection con;
	/**
	 * @return Connection
	 */
public static Connection getConnection() {
		
		String fileName = "db.properties";
		Properties props = new Properties();
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(fileName);
			props.load(fis);
			
			String url = props.getProperty("db.url");
			String un = props.getProperty("db.username");
			String pwd = props.getProperty("db.password");
		
		con = DriverManager.getConnection(url , un , pwd);
		}
		catch (SQLException | IOException e) {
		
			e.printStackTrace();
		}	
		
		return con;
	}
public static void main(String args[])
{
	System.out.println(con);
}
}