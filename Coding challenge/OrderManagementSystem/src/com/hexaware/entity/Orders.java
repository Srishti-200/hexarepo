package com.hexaware.entity;

import java.sql.Date;

public class Orders {
	private int orderId;
    private int userId;
    private int productId;
    private Date orderDate;

    @Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", userId=" + userId + ", productId=" + productId + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void Order(int orderId, int userId, int productId, Date orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.orderDate = orderDate;
    }
}
