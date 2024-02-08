package com.hexaware.main;

import com.hexaware.connectionutil.DBConnection;
import com.hexaware.controller.CourierController;
import com.hexaware.controller.OrderController;
import com.hexaware.entity.Courier;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainModule {
    private static final Map<Integer, String> customerCredentials = new HashMap<>();
    private static final CourierController courierController = new CourierController();
       //   Task 1.3 Authentication
     static {
        customerCredentials.put(1, "password123");
        customerCredentials.put(2, "password456");
    }
    

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Are you a Customer or an Employee?");
        System.out.print("Enter 'C' for Customer or 'E' for Employee: ");
        char userType = scanner.next().charAt(0);

        if (userType == 'C' || userType == 'c') {
            // Customer authentication
            System.out.print("Enter Customer ID: ");
            int customerId = scanner.nextInt();

            if (customerCredentials.containsKey(customerId)) {            // task 1.3
                System.out.print("Enter Password: ");
                String password = scanner.next();

                if (password.equals(customerCredentials.get(customerId))) {
                    System.out.println("Customer authenticated successfully.");
                    System.out.print("Enter Tracking Number to check status: ");
                    String trackingNumberToCheck = scanner.next();
                    String status = courierController.getOrderStatus(trackingNumberToCheck);
                    System.out.println("Status of Order " + trackingNumberToCheck + ": " + status);

                    // Example of cancelOrder usage
                    System.out.print("Enter Tracking Number to cancel order: ");
                    String trackingNumberToCancel = scanner.next();
                    boolean isOrderCancelled = courierController.cancelOrder(trackingNumberToCancel);
                    if (isOrderCancelled) {
                        System.out.println("Order " + trackingNumberToCancel + " cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel order " + trackingNumberToCancel + ".");
                    }

                    // Example of getAssignedOrder usage
                    System.out.print("Enter Courier Staff ID to get assigned orders: ");
                    int courierStaffId = scanner.nextInt();
                    List<Courier> assignedOrders = courierController.getAssignedOrder(courierStaffId);
                    if (!assignedOrders.isEmpty()) {
                        System.out.println("Assigned orders for Courier Staff ID " + courierStaffId + ":");
                        for (Courier order : assignedOrders) {
                            System.out.println("Order ID: " + order.getCourierID() + ", Status: " + order.getStatus());
                        }
                    } else {
                        System.out.println("No assigned orders for Courier Staff ID " + courierStaffId + ".");
                    }

                } else {
                    System.out.println("Invalid customer password.");
                }
            } else {
                System.out.println("Invalid customer ID.");
            }
        } else if (userType == 'E' || userType == 'e') {
            System.out.print("Enter Employee ID: ");
            int employeeId = scanner.nextInt();
            System.out.print("Enter Employee Password: ");
            int password = scanner.nextInt();
            if ((employeeId== 1)&&(password==1234)) {
                System.out.println("Employee authenticated successfully.");

                int choice;
                do {
                    System.out.println("Choose an option:");
                    System.out.println("1. Categorize Parcel");      
                    System.out.println("2. Assign Courier To Shipments");  
                    System.out.println("3. Find Nearest Courier");  
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            Courier courierForCategorization = new Courier(1, "Processing", null, choice);   //  task 1.2  weight of parcel
                            courierForCategorization.categorizeParcel();
                            break;
                        case 2:
                            courierController.assignCourierToShipments();        // task 1.4 Assigning couriers
                            break;
                        case 3:
                            findNearestCourier();
                            break;
                        case 4:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } while (choice != 4);
            } else {
                System.out.println("Invalid employee ID.");
            }
        } else {
            System.out.println("Invalid user type. Please enter 'C' for Customer or 'E' for Employee.");
        }
        Connection connection = null;
        connection = DBConnection.getDBConn();
        System.out.println("************Printing Values from Database*************");
        OrderController oc = new OrderController();
        System.out.println("Type 'Y' for yes and 'N' for no ");
        String userchoice = scanner.next();
        if(userchoice.equals("Y") || userchoice.equals("y"))
        		{
        	String ch =null;
        		
        	do {
        	System.out.println("Enter your choice");
    		System.out.println("1. To insert new order");
    		System.out.println("2. Update courier Status");
    		System.out.println("3. Retrieve courier details using order");
    		System.out.println("4.view revenue report by giving orderid");
    		int choice = scanner.nextInt();
    		switch (choice) {
    		case 1: {
    		    oc.insertOrder(connection);
    			break;
    		}
    		case 2: {
    			oc.UpdateOrderStatus(connection);       
    			break;
    		}
    		case 3: {
    			oc.retrieveOrder(connection);
    			break;
    		}
    		case 4 :{
    			oc.generateRevenueReport(connection);
    		}
    		default: {
    			System.out.println("Enter a valid Choice !!!");
    			break;
    		}
        		}
    		System.out.println("Do u wnt to continue ? Y or y");
    		ch = scanner.next();
    		}while(ch.equals("Y") || ch.equals("y"));
    		System.out.println("Thanks for using our system !!!");
    		System.exit(0);
        		}
    }

    private static void findNearestCourier() {                             // task 3.8 nearest parcel
        Courier[] couriers = {
                new Courier(101, "Available", null, 0),
                new Courier(102, "Available", null, 0),
                new Courier(103, "Available", null, 0)
        };
        System.out.print("Enter order location: ");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter order location: ");
            String orderLocation = scanner.nextLine();
            System.out.println("Entered order location: " + orderLocation);
            courierController.findNearestAvailableCourier(couriers, orderLocation);
        }
         }
}
