package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.model.Employee;
import com.hexaware.util.DBConnection;

public class EmployeeDao {

	Connection con;
	Statement	stmt;
	PreparedStatement ps;
	ResultSet rs;
	
	
	public void createEmployee(Employee emp) {
		try {
			con = DBConnection.getDBConn();
			ps=con.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
			ps.setInt(1, emp.getEmpno());
			ps.setString(2, emp.getEname());
			ps.setInt(3,emp.getSal().getBasic());
			ps.setDouble(4,emp.getSal().getHra());
			ps.setDouble(5,emp.getSal().getPf());
			ps.setDouble(6,emp.getSal().getGross());
			ps.setDouble(7,emp.getSal().getNet());
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " inserted Successfully !!!" );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void showEmployee() {
		try {
			con = DBConnection.getDBConn();
			stmt = con.createStatement();
			rs =stmt.executeQuery("select * from employee");
			while(rs.next()) {
				System.out.println("***************");
				System.out.println("EmpNo " + rs.getInt(1));
				System.out.println("Ename " + rs.getString(2));
				System.out.println("Basic Sal" + rs.getInt(3));
				System.out.println("HRA" + rs.getDouble(4));
				System.out.println("PF" + rs.getDouble(5));
				System.out.println("Gross" + rs.getDouble(6));
				System.out.println("Net Salary" + rs.getDouble(7));
				System.out.println("***************");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void updateEmployee(Employee emp) {
	    try {
	        con = DBConnection.getDBConn();
	        // Assuming your table is named 'employees'
	        ps = con.prepareStatement("UPDATE employee SET ename=?, basic=?, hra=?, pf=?, gross=?, netsalary=? WHERE empno=?");
	        ps.setString(1, emp.getEname());
	        ps.setInt(2, emp.getSal().getBasic());
	        ps.setDouble(3, emp.getSal().getHra());
	        ps.setDouble(4, emp.getSal().getPf());
	        ps.setDouble(5, emp.getSal().getGross());
	        ps.setDouble(6, emp.getSal().getNet());
	        ps.setInt(7, emp.getEmpno());

	        int noofrows = ps.executeUpdate();
	        System.out.println(noofrows + " updated successfully!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


/*	public void deleteEmployee(int empno) {
		try {
			con = DBConnection.getDBConn();
			ps=con.prepareStatement("delete where empno=?");
			ps.setInt(1, empno);
			
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " updated Successfully !!!" );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}*/
}