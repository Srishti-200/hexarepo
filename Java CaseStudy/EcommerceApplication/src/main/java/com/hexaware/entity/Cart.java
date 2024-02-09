package com.hexaware.entity;

public class Cart {
	
	private static int cartId;
    private int customerId;
    private int productId;
    private int quantity;

    // Default constructor
    public Cart() {
    }
    
    /**
     * Cart details
     * @param cartId
     * @param customerId
     * @param productId
     * @param quantity
     */
    public Cart(int cartId, int customerId, int productId, int quantity) {
        Cart.cartId = cartId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public static int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        Cart.cartId = cartId;
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
