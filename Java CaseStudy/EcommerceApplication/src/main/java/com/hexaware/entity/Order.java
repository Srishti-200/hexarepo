package com.hexaware.entity;

import java.sql.Date;

public class Order {
	private int orderId;
    private int customerId;
    private Date orderDate;
    private double totalPrice;
    private String shippingAddress;

    public Order() {
    }
    
    /**
     * Order details
     * 
     * @param orderId
     * @param customerId
     * @param orderDate
     * @param totalPrice
     * @param shippingAddress
     */
    public Order(int orderId, int customerId, Date orderDate, double totalPrice, String shippingAddress) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    /**
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    /**
     * @return total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    /**
     * @return shipping address
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
