package com.hexaware.entity;                       // 5.7: POJO

	import java.sql.Date;
	
	public class Order {
	    private int userID;
	    private int orderID;
	    private int employeeID;
	    private int quantity;
	    private Date orderDate;
	    private Date deliveryDate;
	    private double totalAmount;
	    private String handledEmployee;
	    private String deliveredLocation;
	    private String status;

	    public Order(int userID, int orderID, int employeeID, int quantity, Date orderDate, Date deliveryDate,
	                 double totalAmount, String handledEmployee, String deliveredLocation, String status) {
	        this.userID = userID;
	        this.orderID = orderID;
	        this.employeeID = employeeID;
	        this.quantity = quantity;
	        this.orderDate = orderDate;
	        this.deliveryDate = deliveryDate;
	        this.totalAmount = totalAmount;
	        this.handledEmployee = handledEmployee;
	        this.deliveredLocation = deliveredLocation;
	        this.status = status;
	    }

	    // Getters and Setters for each variable

	    public int getUserID() {
	        return userID;
	    }

	    public void setUserID(int userID) {
	        this.userID = userID;
	    }

	    public int getOrderID() {
	        return orderID;
	    }

	    public void setOrderID(int orderID) {
	        this.orderID = orderID;
	    }

	    public int getEmployeeID() {
	        return employeeID;
	    }

	    public void setEmployeeID(int employeeID) {
	        this.employeeID = employeeID;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public Date getOrderDate() {
	        return orderDate;
	    }

	    public void setOrderDate(Date orderDate) {
	        this.orderDate = orderDate;
	    }

	    public Date getDeliveryDate() {
	        return deliveryDate;
	    }

	    public void setDeliveryDate(Date deliveryDate) {
	        this.deliveryDate = deliveryDate;
	    }

	    public double getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(double totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public String getHandledEmployee() {
	        return handledEmployee;
	    }

	    public void setHandledEmployee(String handledEmployee) {
	        this.handledEmployee = handledEmployee;
	    }

	    public String getDeliveredLocation() {
	        return deliveredLocation;
	    }

	    public void setDeliveredLocation(String deliveredLocation) {
	        this.deliveredLocation = deliveredLocation;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

		@Override
		public String toString() {
			return "Order [userID=" + userID + ", orderID=" + orderID + ", employeeID=" + employeeID + ", quantity="
					+ quantity + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", totalAmount="
					+ totalAmount + ", handledEmployee=" + handledEmployee + ", deliveredLocation=" + deliveredLocation
					+ ", status=" + status + "]";
		}
	    
	}


