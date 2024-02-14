package com.hexaware.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hexaware.dao.OrderProcessorRepository;
import com.hexaware.dao.OrderProcessorRepositoryImpl;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerAlreadyExistsException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.ProductIdAlreadyExistsException;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.main.EcomApp;

public class EcomAppController {
	public static void  startApp()
	{
	   	 OrderProcessorRepositoryImpl orderService = new OrderProcessorRepositoryImpl();
	        Scanner sc = new Scanner(System.in);
	        boolean showMenu = true;
	        int choice;

	        try {
	            do {
	            	 if (showMenu) {
	                     EcomApp.displayMainMenu();
	                    choice = sc.nextInt();
	                } else {
	                    choice = 0;
	                }
	                switch (choice) {
	                    case 1: {
	                        // Customer Management
	                        customerManagement(orderService, sc);
	                        break;
	                    }
	                    case 2: {
	                        // Product Management
	                        productManagement(orderService, sc);
	                        break;
	                    }
	                    case 3: {
	                        // Cart Management
	                        cartManagement(orderService, sc);
	                        break;
	                    }
	                    case 4: {
	                        // Order Management
	                        orderManagement(orderService, sc);
	                        break;
	                    }
	                    case 0:
	                        // Exit
	                        System.out.println("Exiting the application!");
	                        System.out.println("THANKYOU VISIT AGAIN!!!!!!!!!!");
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            } while (choice != 0);
	        } catch (Exception e) {
	            System.out.println("An unexpected error occurred. Exiting the application.");
	        } finally {
	            sc.close();
	        }
	    }

	    private static void customerManagement(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/** Welcome to customer management where you can register new customer,Delete customer
	    	 * @param orderService,sc
	    	 */
	        int customerChoice;
	        do {
	    	System.out.println("\033[1;36m1. Customer Management\033[0m");
	        System.out.println("1. Register Customer");
	        System.out.println("2. Delete Customer");
	        System.out.println("0.Return to MainMenu");
	        System.out.print("Enter your choice: ");
	         customerChoice = sc.nextInt();
	        switch (customerChoice) {
	            case 1:
	                // Register Customer
	                registerCustomer(orderService, sc);
	                break;
	            case 2:
	                // Delete Customer
	                deleteCustomer(orderService, sc);
	                break;
	            case 0:
	                      System.out.println("Returning to Main Menu");
	                      break;
	             default:
	                System.out.println("Invalid choice for Customer Management.");
	        }
	    }while(customerChoice !=0);
	    }

	    private static void registerCustomer(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/** Makes customer registration 
	    	 * throws exception when there is no new customer id given
	    	 */
	        try {
	            System.out.print("Enter customer Id: ");
	            int customerId = sc.nextInt();
	            sc.nextLine(); // Consume the newline character
	            if (orderService.isCustomerExists(customerId)) {
	                throw new CustomerAlreadyExistsException("Customer with ID " + customerId + " already exists");
	            }

	            System.out.print("Enter customer name: ");
	            String customerName = sc.nextLine();
	            System.out.print("Enter customer email: ");
	            String customerEmail = sc.nextLine();
	            System.out.print("Enter customer password: ");
	            String customerPassword = sc.nextLine();
	            Customer customer = new Customer(customerId, customerName, customerEmail, customerPassword);
	            orderService.createCustomer(customer);
	            System.out.println("Customer registered successfully!");
	        } catch (CustomerAlreadyExistsException e) {
	            System.out.println(e.getMessage());
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	            }
	    }

	    private static void deleteCustomer(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/** Deletes the existing customer
	    	 *  Only when you know the customer id
	    	 */
	        System.out.print("Enter Customer ID to delete: ");
	        int customerIdToDelete = sc.nextInt();
	        boolean deleted = orderService.deleteCustomer(customerIdToDelete);
	        if (deleted) {
	            System.out.println("Customer deleted successfully!");
	        } else {
	            System.out.println("Customer not found. Deletion failed.");
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	        }
	    }
	    private static void productManagement(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/**
	    	 * Manages the products in the company.
	    	 * Gives access like view products,add them and delete them.
	    	 */
	    	int productChoice;
	    	do {
	    	System.out.println("\033[1;35m2. Product Management\033[0m");
	        System.out.println("1. View Products");
	        System.out.println("2. Add Product");
	        System.out.println("3. Delete Product");
	        System.out.println("0.Return to MainMenu");
	        System.out.print("Enter your choice: ");
	         productChoice = sc.nextInt();
	        switch (productChoice) {
	            case 1:
	                // View Products
	                viewProducts(orderService);
	                break;
	            case 2:
	                // Add Product
	                addProduct(orderService, sc);
	                break;
	            case 3:
	                // Delete Product
	                deleteProduct(orderService, sc);
	                break;
	            case 0:
	            	   System.out.println("Retruning to MainMenu");
	            	   break;
	            default:
	                System.out.println("Invalid choice for Product Management.");
	        }
	    }while(productChoice !=0);
	    }

	    private static void viewProducts(OrderProcessorRepositoryImpl orderService) {
	    	/**
	    	 * To view products.
	    	 */
	        List<Product> products = orderService.viewAllProducts();
	        System.out.println("List of Products:");
	        for (Product product : products) {
	            System.out.println(product);
	        }
	    }
	    private static void addProduct(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/**
	    	 * To add new product
	    	 */
	        System.out.println("Enter product details:");
	        System.out.print("Enter product id: ");
	        int productId = sc.nextInt();
	        sc.nextLine(); // Consume the newline character
	        if (orderService.isProductExists(productId)) {
	            System.out.println("Product with ID " + productId + " already exists");
	            return;
	        }

	        System.out.print("Enter product name: ");
	        String productName = sc.nextLine();
	        System.out.print("Enter product price: ");
	        double productPrice = sc.nextDouble();
	        sc.nextLine();
	        System.out.print("Enter product description: ");
	        String productDescription = sc.nextLine();
	        System.out.print("Enter stock quantity: ");
	        int stockQuantity = sc.nextInt();
	        Product product = new Product(productId, productName, productPrice, productDescription, stockQuantity);

	        try {
	            boolean productAdded = orderService.createProduct(product);
	            if (productAdded) {
	                System.out.println("Product added successfully!");
	            } else {
	                System.out.println("Failed to add the product. Please try again.");
	            }
	        } catch (ProductIdAlreadyExistsException e) {
	            System.out.println(e.getMessage());
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	        }
	    }

	    private static void deleteProduct(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	        try {
	            System.out.print("Enter Product ID to delete: ");
	            int productIdToDelete = sc.nextInt();
	            boolean deleted = orderService.deleteProduct(productIdToDelete);
	            if (deleted) {
	                System.out.println("Product deleted successfully!");
	            } else {
	                System.out.println("Product not found. Deletion failed.");
	            }
	        } catch (ProductNotFoundException e) {
	            System.out.println("Product not found: " + e.getMessage());
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	        } 
	    }


	    private static void cartManagement(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/**
	    	 * Orders of each customer is handled.
	    	 */
	     int cartChoice;
	     do {
	    	System.out.println("\033[1;33m3. Cart Management\033[0m");
	        System.out.println("1. View Cart");
	        System.out.println("2. Add to Cart");
	        System.out.println("3. Remove from Cart");
	        System.out.println("0.Retrun to MainMenu");
	        System.out.print("Enter your choice: ");
	        cartChoice = sc.nextInt();
	        switch (cartChoice) {
	            case 1:
	                // View Cart
	                viewCart(orderService, sc);
	                break;
	            case 2:
	                // Add to Cart
	                addToCart(orderService, sc);
	                break;
	            case 3:
	                // Remove from Cart
	                removeFromCart(orderService, sc);
	                break;
	            case 0:
	            	System.out.println("Returning to MainMenu");
	            	break;
	            default:
	                System.out.println("Invalid choice for Cart Management.");
	        }
	    }while(cartChoice !=0);
	    }

	    private static void viewCart(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	        /**
	         * To view the cart and its products
	         */
	        System.out.print("Enter Customer ID: ");
	        int customerIdToViewCart = sc.nextInt();
	        Customer customerToViewCart = new Customer(customerIdToViewCart);

	        try {
	            List<Product> cartProducts = orderService.viewCart(customerToViewCart);
	            System.out.println("Products in the cart:");
	            for (Product cartProduct : cartProducts) {
	                System.out.println(cartProduct);
	            }
	        } catch (CustomerNotFoundException e) {
	            System.out.println("Error: " + e.getMessage());
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	        }
	    }

	    private static void addToCart(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/**
	    	 * Add products to cart
	    	 */
	        System.out.print("Enter Cart ID: ");
	        int cartIdToAddToCart = sc.nextInt();
	        System.out.print("Enter Customer ID: ");
	        int customerIdToAddToCart = sc.nextInt();
	        System.out.print("Enter Product ID to add to cart: ");
	        int productIdToAddToCart = sc.nextInt();
	        System.out.print("Enter quantity: ");
	        int quantityToAddToCart = sc.nextInt();
	        try {
	            orderService.addToCart(cartIdToAddToCart, customerIdToAddToCart, productIdToAddToCart, quantityToAddToCart);
	            System.out.println("Product added to cart successfully!");
	        } catch (CustomerNotFoundException e) {
	            System.out.println("Customer not found. Add to Cart failed");
	            e.printStackTrace();
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	        }
	    }
	    private static void removeFromCart(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/** 
	    	 * To remove product from cart.
	    	 */
	        try {
	            System.out.print("Enter Customer ID: ");
	            int customerIdToRemoveFromCart = sc.nextInt();
	            sc.nextLine(); 
	            System.out.print("Enter Product ID to remove from cart: ");
	            int productIdToRemoveFromCart = sc.nextInt();

	            Customer customerToRemoveFromCart = new Customer(customerIdToRemoveFromCart);
	            Product productToRemoveFromCart = new Product(productIdToRemoveFromCart);
	            boolean removedFromCart = orderService.removeFromCart(customerToRemoveFromCart, productToRemoveFromCart);

	            if (removedFromCart) {
	                System.out.println("Product removed from cart successfully!");
	            } else {
	                System.out.println("Failed to remove product from cart. Please try again.");
	            }
	        } catch (ProductNotFoundException e) {
	            System.out.println("Product not found: " + e.getMessage());
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	            } 
	    }


	    private static void orderManagement(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	    	/**
	    	 * Manages all the order related details
	    	 */
	        int orderChoice;
	        do {
	    	System.out.println("\033[1;34m4. Order Management\033[0m");
	        System.out.println("1. Place Order");
	        System.out.println("2. View Customer Order");
	        System.out.println("0.Return to main Manu");
	        System.out.print("Enter your choice: ");
	         orderChoice = sc.nextInt();
	        switch (orderChoice) {
	            case 1:
	                // Place Order
	                placeOrder(orderService, sc);
	                break;
	            case 2:
	                // View Customer Order
	                viewCustomerOrder(orderService, sc);
	                break;
	            case 0:
	            	System.out.println("Returning to Mainmenu");
	            	break;
	            default:
	                System.out.println("Invalid choice for Order Management.");
	        }
	    }while(orderChoice!=0);
	    }
	    private static void placeOrder(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	        /**
	         * To place a new order.
	         */
	        System.out.println("Enter your customer ID: ");
	        int customerId = sc.nextInt();

	        if (orderService.isCustomerExists(customerId)) {
	            System.out.println("Do you want to view your cart? (yes/no)");
	            String viewCartOption = sc.next();

	            if (viewCartOption.equalsIgnoreCase("yes")) {
	                try {
	                    Customer customer = orderService.getCustomerById(customerId);
	                    List<Product> cartProducts = orderService.viewCart(customer);

	                    if (cartProducts.isEmpty()) {
	                        System.out.println("Your cart is empty.");
	                    } else {
	                        System.out.println("Your Cart:");
	                        for (Product product : cartProducts) {
	                            System.out.println(product.getName() + " - Price: " + product.getPrice());
	                        }

	                        // Prompt user to place order
	                        System.out.println("Do you want to place the order? (yes/no)");
	                        String placeOrderOption = sc.next();
	                        if (placeOrderOption.equalsIgnoreCase("yes")) {
	                            placeOrderProceed(orderService, sc, customerId);
	                        } else {
	                            System.out.println("THANKKKYOUUU FOR DOING NOTHING!!!!!!");
	                        }
	                    }
	                } catch (CustomerNotFoundException e) {
	                    e.printStackTrace(); 
	                }
	            } else {
	                placeOrderProceed(orderService, sc, customerId);
	            }
	        } else {
	            System.out.println("Customer with ID " + customerId + " not found.");
	        }
	    }

	    private static void placeOrderProceed(OrderProcessorRepositoryImpl orderService, Scanner sc, int customerId) {
	        List<Map<Product, Integer>> productsAndQuantities = new ArrayList<>();

	        boolean addingMoreProducts = true;
	        while (addingMoreProducts) {
	            System.out.println("Enter product ID: ");
	            int productId = sc.nextInt();
	            try {
	                Product product = orderService.getProductById(productId);
	                if (product != null) {
	                    System.out.println("Product Name: " + product.getName());
	                    System.out.println("Product Price: " + product.getPrice());
	                    System.out.println("Enter quantity: ");
	                    int quantity = sc.nextInt();
	                    Map<Product, Integer> productMap = new HashMap<>();
	                    productMap.put(product, quantity);
	                    productsAndQuantities.add(productMap);
	                    System.out.println("Do you want to add more products? (yes/no)");
	                    String addMoreOption = sc.next();
	                    if (addMoreOption.equalsIgnoreCase("no")) {
	                        addingMoreProducts = false;
	                    }
	                } else {
	                    System.out.println("Product with ID " + productId + " not found.");
	                }
	            } catch (ProductNotFoundException e) {
	                System.out.println("Error: Product with ID " + productId + " not found.");
	            }
	        }
	        sc.nextLine(); 
	        System.out.println("Enter shipping address: ");
	        String shippingAddress = sc.nextLine();

	        try {
	            boolean orderPlaced = orderService.placeOrder(customerId, productsAndQuantities, shippingAddress);
	            if (orderPlaced) {
	                System.out.println("Order placed successfully!");
	            } else {
	                System.out.println("Failed to place order. Please try again.");
	            }
	        } catch (CustomerNotFoundException | ProductNotFoundException e) {
	            e.printStackTrace(); // Handle exceptions appropriately
	        }
	    }

	    private static void viewCustomerOrder(OrderProcessorRepositoryImpl orderService, Scanner sc) {
	        System.out.print("Enter Customer ID: ");
	        int customerIdToViewOrder = sc.nextInt();

	        try {
	            List<Map<Product, Integer>> customerOrders = orderService.getOrdersByCustomer(customerIdToViewOrder);
	            System.out.println("Customer orders:");
	            for (Map<Product, Integer> order : customerOrders) {
	                for (Map.Entry<Product, Integer> entry : order.entrySet()) {
	                    System.out.println("Product: " + entry.getKey() + ", Quantity: " + entry.getValue());
	                }
	            }
	        } catch (CustomerNotFoundException e) {
	            System.out.println("Error: " + e.getMessage());
	            System.out.println("Exiting the system because of exception raised");
	            System.exit(1);
	        }
	    }
	}


