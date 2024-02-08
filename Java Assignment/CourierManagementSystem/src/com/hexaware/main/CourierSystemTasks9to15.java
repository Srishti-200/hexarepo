package com.hexaware.main;
	import java.security.SecureRandom;
	import java.util.Scanner;

	public class CourierSystemTasks9to15 {

	    private static String[][] parcelData = {
	            {"80056", "In transit"},
	            {"80057", "Out for delivery"},
	            {"80058", "Delivered"}
	    };

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	       // Task 4. 9: Parcel Tracking
	        System.out.print("Enter parcel tracking number: ");
	        String inputTrackingNumber = scanner.nextLine();
	        trackParcel(inputTrackingNumber);

	        // Task 4.10: Customer Data Validation
	        boolean isValid = validateCustomerInfo("Akshay", "15th main road mylapore", "600-07");
	        System.out.println("Customer data is valid: " + isValid);

	        // Task 4.11: Address Formatting
	        String formattedAddress = formatAddress("45 Leo street", "Pallavaram", "Tamilnadu", "600087");
	        System.out.println("Formatted Address: " + formattedAddress);

	        // Task 4.12: Order Confirmation Email
	        String emailContent = generateOrderConfirmationEmail("Akashay", "Order125", "", "Delivery Date: Feb 17, 2024");
	        System.out.println("Order Confirmation Email:\n" + emailContent);

	        // Task 4.13: Calculate Shipping Costs
	        double shippingCost = calculateShippingCost("vandalur", "chennai", 5.0);
	        System.out.println("Shipping Cost: $" + shippingCost);

	        // Task 4.14: Password Generator
	        String generatedPassword = generatePassword();
	        System.out.println("Generated Password: " + generatedPassword);
	        findSimilarAddresses("5th cross street", "park street", "gandhi nagar");
	        scanner.close();
	    }

	    // Task 9: Parcel Tracking
	    private static void trackParcel(String trackingNumber) {
	        boolean found = false;
	        for (String[] parcel : parcelData) {
	            if (parcel[0].equals(trackingNumber)) {
	                found = true;
	                displayParcelStatus(parcel[1]);
	                break;
	            }
	        }
	        if (!found) {
	            System.out.println("Parcel not found.");
	        }
	    }

	    // Task 10: Customer Data Validation
	    private static boolean validateCustomerInfo(String name, String address, String phoneNumber) {
	        return name.matches("[A-Za-z ]+") &&
	                address.matches("[A-Za-z0-9 ]+") &&
	                phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
	    }

	    // Task 11: Address Formatting
	    private static String formatAddress(String street, String city, String state, String zipCode) {
	        StringBuilder formattedAddress = new StringBuilder();
	        formattedAddress.append(capitalizeFirstLetter(street)).append(", ")
	                .append(capitalizeFirstLetter(city)).append(", ")
	                .append(capitalizeFirstLetter(state)).append(" ")
	                .append(zipCode);
	        return formattedAddress.toString();
	    }

	    private static String capitalizeFirstLetter(String str) {
	        return str.substring(0, 1).toUpperCase() + str.substring(1);
	    }

	    // Task 12: Order Confirmation Email
	    private static String generateOrderConfirmationEmail(String customerName, String orderNumber, String deliveryAddress, String deliveryDate) {
	        return "Dear " + customerName + ",\n\n" +
	                "Thank you for your order!\n" +
	                "Order Number: " + orderNumber + "\n" +
	                "Delivery Address: " + deliveryAddress + "\n" +
	                "Expected Delivery Date: " + deliveryDate + "\n\n" +
	                "Best regards,\nThe Courier System";
	    }

	    // Task 13: Calculate Shipping Costs
	    private static double calculateShippingCost(String source, String destination, double parcelWeight) {
	        double distanceCost = getDistanceCost(source, destination);
	        double weightCost = getWeightCost(parcelWeight);
	        return distanceCost + weightCost;
	    }

	    private static double getDistanceCost(String source, String destination) {
	        return 0.1 * Math.abs(source.length() - destination.length());
	    }

	    private static double getWeightCost(double parcelWeight) {
	        return 0.5 * parcelWeight;
	    }

	    // Task 14: Password Generator
	    private static String generatePassword() {
	        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
	        String numberChars = "0123456789";
	        String specialChars = "!@#$%^&*()-=_+[]{}|;':,.<>?";

	        String allChars = upperChars + lowerChars + numberChars + specialChars;

	        SecureRandom random = new SecureRandom();
	        StringBuilder password = new StringBuilder();

	        for (int i = 0; i < 12; i++) {
	            password.append(allChars.charAt(random.nextInt(allChars.length())));
	        }
	        return password.toString();
	    }

	    // Task 4.15: Find Similar Addresses
	    private static void findSimilarAddresses(String... addresses) {
	        for (int i = 0; i < addresses.length - 1; i++) {
	            for (int j = i + 1; j < addresses.length; j++) {
	                if (areAddressesSimilar(addresses[i], addresses[j])) {
	                    System.out.println("Similar addresses found: " + addresses[i] + " and " + addresses[j]);
	                }
	            }
	        }
	    }

	    private static boolean areAddressesSimilar(String address1, String address2) {
	       return address1.equals(address2);
	    }
	    // task 1.1 Deliver status 
	    private static void displayParcelStatus(String status) {              
	        System.out.println("Parcel status: " + status);
	    }
	}


