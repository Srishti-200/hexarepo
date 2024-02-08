package com.hexaware.controller;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import com.hexaware.dao.OrderManagementRepositoryImpl;
import com.hexaware.entity.Products;
import com.hexaware.entity.User;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.UserNotFoundException;

public class OrderProcessor { 
    public static void startApp() throws SQLException {
    	   OrderManagementRepositoryImpl orderService = new OrderManagementRepositoryImpl();
    	Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. createUser");
            System.out.println("2. createProduct");
            System.out.println("3.createOrder");
            System.out.println("4. cancelOrder");
            System.out.println("5. getAllProducts");
            System.out.println("6. getOrderbyUser");
            System.out.println("7. exit");
            System.out.println("Enter your Choice:");
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter User Id: ");
                        int customerId = sc.nextInt();
                        sc.nextLine(); 
                        if (orderService.userExists(customerId)) {
                            throw new UserNotFoundException("Customer with ID " + customerId + " already exists");
                        }
                        System.out.println("Enter user detail:");
                        System.out.print("User ID: ");
                        int userId = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Username: ");
                        String username = sc.nextLine();
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        System.out.print("Role: ");
                        String role = sc.nextLine();
                        User user = new User(userId, username, password, role);
                        System.out.println("User Added Successfully");
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Exiting the system because of exception raised");
                        System.exit(1);
                    }
                    break;
                case 2:
                    {
                        System.out.println("Enter product details");
                        System.out.print("Product ID: ");
                        int productId = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Product Name: ");
                        String productName = sc.nextLine();
                        System.out.print("Description: ");
                        String description = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Quantity in Stock: ");
                        int quantityInStock = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Type: ");
                        String type = sc.nextLine();
                        Products product = new Products(productId, productName, description, price, quantityInStock, type);
                        try {
                            orderService.createProduct(null, product);
                            System.out.println("Product Added Successfully");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                {
                    System.out.println("Enter order details");
                    System.out.print("Order ID: ");
                    int orderId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Product ID: ");
                    int productId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Order Date (yyyy-MM-dd): ");
                    String orderDateString = sc.nextLine();
                    Date orderDate = Date.valueOf(orderDateString);                   
                    try {
                        orderService.createOrder(userId, productId, orderId, orderDate);
                        System.out.println("Order created successfully.");
                    }catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Exiting the system because of orderIDalreadyexists ");
                        System.exit(1);
                    }catch (SQLException e) {
                        System.out.println("Error creating order: " + e.getMessage());
                    }
                }
                break;

                case 4:
                {
                    System.out.print("Enter order ID: ");
                    int cancelOrderId = sc.nextInt();
                    sc.nextLine(); 
                    try {
                        orderService.cancelOrder( cancelOrderId);
                        System.out.println("Order canceled successfully.");
                    } catch (OrderNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Exiting the system because ordernotfoundexception raised");
                        System.exit(1);
                    } catch (Exception e) {
                        System.out.println("An error occurred while canceling the order: " + e.getMessage());
                    }
                }
                break;

                case 5:
                    {
                        System.out.println("All Products:");
                        for (Products p : orderService.getAllProducts()) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 6:
                    {
                        System.out.print("Enter user ID: ");
                        int userIdForOrder = sc.nextInt();
                        sc.nextLine(); 
                        User userForOrder = new User(userIdForOrder, "", "", ""); // Dummy user
                        System.out.println("Orders by User:");
                        for (Products p : orderService.getOrderByUser(userForOrder)) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
