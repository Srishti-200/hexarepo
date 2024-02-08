package com.hexaware.dao;           // Task 9.2 CourierServiceDb
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.connectionutil.DBConnection;                                             // 9.2  courierservice in dao
import com.hexaware.entity.Order;

public class CourierServiceDb {
		Connection con;
		Statement	stmt;
		PreparedStatement ps;
		ResultSet rs;
		
		public void createOrder(Order o) {
			try {
				con = DBConnection.getDBConn();
				ps=con.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, o.getUserID());
				ps.setInt(2, o.getOrderID());
				ps.setInt(3,o.getEmployeeID());
				ps.setInt(4,o.getQuantity());
				ps.setDate(5,o.getOrderDate());
				ps.setDate(6,o.getDeliveryDate());
			    ps.setDouble(7,o.getTotalAmount());
			    ps.setString(8,o.getHandledEmployee());
				ps.setString(9,o.getDeliveredLocation());
				ps.setString(10,o.getStatus());
				int noofrows = ps.executeUpdate();
				System.out.println(noofrows + " inserted Successfully !!!" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		public Order retrieveOrder(Connection connection, int orderID) {
	        Order order = null;
	        try {
	            ps = connection.prepareStatement("SELECT * FROM orders WHERE orderID = ?");
	            ps.setInt(1, orderID);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                order = new Order(
	                        rs.getInt("userID"),
	                        rs.getInt("orderID"),
	                        rs.getInt("employeeID"),
	                        rs.getInt("quantity"),
	                        rs.getDate("orderDate"),
	                        rs.getDate("deliveryDate"),
	                        rs.getDouble("totalAmount"),
	                       rs.getString("Handeledemployee"),
	                        rs.getString("deliveredLocation"),
	                        rs.getString("status")
	                );
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return order;
	    }

	    public void updateOrderStatus(Connection connection, Order order) {
	        try {
	            ps = connection.prepareStatement("UPDATE orders SET status = ? WHERE orderID = ?");
	            ps.setString(1, order.getStatus());
	            ps.setInt(2, order.getOrderID());

	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Order status updated in the database");
	            } else {
	                System.out.println("Failed to update order status");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	    public double generateRevenueReport(Connection connection, int orderID) {                      // 9.4 generate report
	        double orderRevenue = -1;
	        try {
	            PreparedStatement ps = connection.prepareStatement("SELECT SUM(totalAmount) AS totalRevenue FROM orders WHERE orderID = ?");
	            ps.setInt(1, orderID);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                orderRevenue = rs.getDouble("totalRevenue");
	                System.out.println("Total Revenue for Order ID " + orderID + ": $" + orderRevenue);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderRevenue;
	    }

		
}

