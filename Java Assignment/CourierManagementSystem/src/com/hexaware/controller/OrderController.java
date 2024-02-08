package com.hexaware.controller;

import com.hexaware.dao.CourierServiceDb;
import com.hexaware.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class OrderController {                                                                                // task 9.3 to insert,retrieve and change status
    private Scanner scanner = new Scanner(System.in);
    private CourierServiceDb courierServiceDb = new CourierServiceDb();

    public void insertOrder(Connection connection) {
        try {
            System.out.print("Enter userID: ");
            int userID = scanner.nextInt();

            System.out.print("Enter orderID: ");
            int orderID = scanner.nextInt();

            System.out.print("Enter employeeID: ");
            int employeeID = scanner.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Enter orderDate (YYYY-MM-DD): ");
            String orderDateStr = scanner.next();
            java.sql.Date orderDate = java.sql.Date.valueOf(orderDateStr);

            System.out.print("Enter deliveryDate (YYYY-MM-DD): ");
            String deliveryDateStr = scanner.next();
            java.sql.Date deliveryDate = java.sql.Date.valueOf(deliveryDateStr);

            System.out.print("Enter totalAmount: ");
            double totalAmount = scanner.nextDouble();

            System.out.print("Enter handledEmployee: ");
            String handledEmployee = scanner.next();

            System.out.print("Enter deliveredLocation: ");
            String deliveredLocation = scanner.next();

            System.out.print("Enter status: ");
            String status = scanner.next();

            Order order = new Order(userID, orderID, employeeID, quantity, orderDate, deliveryDate, totalAmount,
                    handledEmployee, deliveredLocation, status);

            courierServiceDb.createOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateOrderStatus(Connection connection) {
        try {
            System.out.print("Enter order ID to retrieve: ");
            int orderID = scanner.nextInt();
            Order order = courierServiceDb.retrieveOrder(connection, orderID);

            if (order != null) {
                System.out.println("Retrieved Order: " + order);
                System.out.print("Enter new status: ");
                String newStatus = scanner.next();
                order.setStatus(newStatus);
                courierServiceDb.updateOrderStatus(connection, order);

                System.out.println("Order status updated successfully");
            } else {
                System.out.println("Order not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Order retrieveOrder(Connection connection) {
        Order order = null;
        try {
            System.out.print("Enter orderID to retrieve: ");
            int orderID = scanner.nextInt();
            
            order = courierServiceDb.retrieveOrder(connection, orderID);

            if (order != null) {
                System.out.println("Retrieved Order: " + order);
            } else {
                System.out.println("Order not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
    public void generateRevenueReport(Connection connection) {                   // task 9.4 generate report
        try {
            System.out.print("Enter order ID to generate revenue report: ");
            int orderID = scanner.nextInt();

            double orderRevenue = courierServiceDb.generateRevenueReport(connection, orderID);
            if (orderRevenue != -1) {
                System.out.println("Revenue for Order ID " + orderID + ": $" + orderRevenue);
            } else {
                System.out.println("Order not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	}
    

