package com.hexaware.entity;

public class Product {

    private int productId;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    private int quantityInCart; // New field for quantity in cart

    // Default constructor
    public Product() {
    }

    public Product(int productId) {
        this.productId = productId;
    }

    /**
     * Constructor with all parameters
     */
    public Product(int productId, String name, double price, String description, int stockQuantity, int quantityInCart) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.quantityInCart = quantityInCart;
    }

    // Getters and setters for all fields

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", description="
                + description + ", stockQuantity=" + stockQuantity + ", quantityInCart=" + quantityInCart + "]";
    }
}
