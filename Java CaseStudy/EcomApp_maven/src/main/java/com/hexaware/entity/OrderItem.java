package com.hexaware.entity;

public class OrderItem {
	private int orderItemId;
	private int orderId;
	private int productId;
	private int quantity;

	// Default constructor
	public OrderItem() {
	}

	/**
	 * Order item details
	 * 
	 * @param orderItemId
	 * @param orderId
	 * @param productId
	 * @param quantity
	 */
	public OrderItem(int orderItemId, int orderId, int productId, int quantity) {
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	/**
	 * @return order item id
	 */
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * @return order id
	 */
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return product id
	 */
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
