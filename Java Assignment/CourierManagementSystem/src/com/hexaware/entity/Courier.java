package com.hexaware.entity;               // 5.2: POJO

import java.util.Scanner;

public class Courier {
	private int courierID;
	private String status;
	private String location;
	private String trackingNumber;
	private static int uniqueTrackingNumber = 1000;
	private int courierStaffId;

	public Courier(int courierID, String status, String location, int courierStaffId) {
		this.courierID = courierID;
		this.status = status;
		this.location = location;
		this.trackingNumber = generateTrackingNumber();
		this.courierStaffId = courierStaffId;
	}

	public void checkDeliveryStatus() {
		System.out.print("Courier ID: " + this.getCourierID() + ", Status: " + this.getStatus() + " - ");
		if ("Delivered".equals(this.getStatus())) {
			System.out.println("The order has been delivered.");
		} else if ("Processing".equals(this.getStatus())) {
			System.out.println("The order is still in processing.");
		} else if ("Cancelled".equals(this.getStatus())) {
			System.out.println("The order has been cancelled.");
		} else {
			System.out.println("Invalid order status.");
		}
	}

	public synchronized String generateTrackingNumber() {
		return String.valueOf(uniqueTrackingNumber++);
	}

	public String getStatus() {
		return status;
	}

	public int getCourierID() {
		return courierID;
	}

	private void setCourierID(int courierID) {
		this.courierID = courierID;
	}

	public void categorizeParcel() {
		System.out.print("Enter the weight of the parcel: ");
		Scanner sc = new Scanner(System.in);
		double weight = sc.nextDouble();

		String category;
		switch (Double.compare(weight, 5.0)) {
		case -1:
			category = "Light";
			break;
		case 0:
			category = "Medium";
			break;
		case 1:
			category = "Heavy";
			break;
		default:
			category = "Invalid";
			break;
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCourierStaffId() {
		return courierStaffId;
	}

	public void setCourierStaffId(int courierStaffId) {
		this.courierStaffId = courierStaffId;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}
}
