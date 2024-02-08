package com.hexaware.entity;

public class Product {
	
	@Override
	/**
	 * @return Product details
	 */
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", description="
				+ description + ", stockQuantity=" + stockQuantity + "]";
	}

	private int productId;
	private String name;
	private double price;
	private String description;
	private int stockQuantity;

	// Default constructor
	public Product() {
	}
	
	public Product(int productId) {
		this.productId = productId;
	}

	/**
	 * @param productId
	 * @param name
	 * @param price
	 * @param description
	 * @param stockQuantity
	 */
	public Product(int productId, String name, double price, String description, int stockQuantity) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
