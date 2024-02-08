package com.hexaware.controller;

import com.hexaware.dao.ICourierUserService;
import com.hexaware.entity.Courier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourierController implements ICourierUserService {
    private static List<Courier> courierOrders = new ArrayList<>();
    private Map<Integer, String> courierStatusMap;

    public CourierController() {
        this.courierStatusMap = new HashMap<>();
        this.courierStatusMap.put(101, "Delivered");
        this.courierStatusMap.put(102, "Processing");
        this.courierStatusMap.put(103, "Cancelled");
        this.courierStatusMap.put(104, "In transit");
        this.courierStatusMap.put(105, "Processing");
    }

    public void checkDeliveryStatus(int courierIdToCheck) {
        String statusToCheck = this.courierStatusMap.getOrDefault(courierIdToCheck, "Unknown");
        Courier courier = new Courier(courierIdToCheck, statusToCheck, null, courierIdToCheck);
        courier.checkDeliveryStatus();
    }

    public void assignCourierToShipments() {
        List<Shipment> shipments = createSampleShipments();
        for (Shipment shipment : shipments) {
            categorizeShipment(shipment);
        }
    }

    private List<Shipment> createSampleShipments() {
        // Create sample shipments with different weights
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment(101, 5.0));
        shipments.add(new Shipment(102, 10.0));
        shipments.add(new Shipment(103, 2.0));
        return shipments;
    }

    private void categorizeShipment(Shipment shipment) {
        double weight = shipment.getWeight();

        if (weight <= 5.0) {
            System.out.println("Shipment ID: " + shipment.getShipmentId() + ", Category: Light");
        } else if (weight > 5.0 && weight <= 10.0) {
            System.out.println("Shipment ID: " + shipment.getShipmentId() + ", Category: Medium");
        } else {
            System.out.println("Shipment ID: " + shipment.getShipmentId() + ", Category: Heavy");
        }
    }

    private static class Shipment {
        private int shipmentId;
        private double weight;

        public Shipment(int shipmentId, double weight) {
            this.shipmentId = shipmentId;
            this.weight = weight;
        }

        public int getShipmentId() {
            return shipmentId;
        }

        public double getWeight() {
            return weight;
        }
    }

    private double calculateDistance(String location1, String location2) {
        if (location1 != null && location2 != null) {
            return Math.abs(location1.hashCode() - location2.hashCode());
        } else {
            return Double.MAX_VALUE;
        }
    }

    @Override
    public String placeOrder(Courier courierObj) {
        courierObj.generateTrackingNumber(); // Corrected method call
        courierOrders.add(courierObj);
        return courierObj.getTrackingNumber();
    }

    @Override
    public String getOrderStatus(String trackingNumber) {
        Optional<Courier> foundCourier = courierOrders.stream()
                .filter(c -> c.getTrackingNumber().equals(trackingNumber))
                .findFirst();
        return foundCourier.map(Courier::getStatus).orElse("Order not found");
    }

    @Override
    public boolean cancelOrder(String trackingNumber) {
        return courierOrders.removeIf(c -> c.getTrackingNumber().equals(trackingNumber));
    }

    public List<Courier> getAssignedOrder(int courierStaffId) {
        return courierOrders.stream()
                .filter(c -> c.getCourierStaffId() == courierStaffId)
                .collect(Collectors.toList());
    }

    public List<Courier> getCourierOrders() {
        return courierOrders;
    }

    public void setCourierOrders(List<Courier> courierOrders) {
        this.courierOrders = courierOrders;
    }

    public void findNearestAvailableCourier(Courier[] couriers, String orderLocation) {
        Courier nearestCourier = null;
        double minDistance = Double.MAX_VALUE;

        for (Courier courier : couriers) {
            if ("Available".equals(courier.getStatus())) {
                double distance = calculateDistance(orderLocation, courier.getLocation());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCourier = courier;
                }
            }
        }

        if (nearestCourier != null) {
            System.out.println("Nearest Available Courier:");
            System.out.println("Courier ID: " + nearestCourier.getCourierID());
            System.out.println("Distance: " + minDistance);
        } else {
            System.out.println("No available couriers found.");
        }
    }

    private double calculateDistance1(String location1, String location2) {
        if (location1 != null && location2 != null) {
            return Math.abs(location1.length() - location2.length());
        } else {
            return Double.MAX_VALUE;
        }
    }
}
