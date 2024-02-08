package com.hexaware.main;

import java.util.ArrayList;
import java.util.Scanner;

class Order {
    int orderId;
    String customerName;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }
}

class Courier {
    double currentLocation;
    double destination;

    public Courier(double destination) {
        this.currentLocation = 0.0; // Initial location
        this.destination = destination;
    }

    public void move() {
        // Simulate courier movement (you can replace this logic with actual tracking)
        currentLocation += Math.random() * 10; // Move a random distance between 0 and 10
    }

    public boolean hasReachedDestination() {
        return currentLocation >= destination;
    }
}

public class OrderTrackingtask5to6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Display orders for a specific customer");
        System.out.println("2. Track the real-time location of a courier");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                displayOrders();
                break;
            case 2:
                trackCourier();
                break;
            default:
                System.out.println("Invalid option. Exiting program.");
        }
    }

    private static void displayOrders() {
        // Orders data
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "Jessica"));
        orders.add(new Order(2, "James"));
        orders.add(new Order(3, "Jessica"));
        orders.add(new Order(4, "Bala"));
           Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the customer name:");
        String targetCustomer = scanner.nextLine();

          System.out.println("Orders for customer " + targetCustomer + ":");
        for (Order order : orders) {
            if (order.customerName.equals(targetCustomer)) {
                System.out.println("Order ID: " + order.orderId);
            }
        }
    }

    private static void trackCourier() {
        
        double destination = 50.0;
        Courier courier = new Courier(destination);

          System.out.println("Courier is on the way to destination...");

        while (!courier.hasReachedDestination()) {
            courier.move();
            System.out.println("Current Location: " + courier.currentLocation);
               try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Courier has reached the destination!");
    }
}
