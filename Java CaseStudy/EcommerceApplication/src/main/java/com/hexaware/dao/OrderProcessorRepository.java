package com.hexaware.dao;

import java.util.List;
import java.util.Map;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerAlreadyExistsException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductIdAlreadyExistsException;
import com.hexaware.exception.ProductNotFoundException;

/**
 * Repository for managing customers, products, shopping carts, and orders in an e-commerce application.
 */
public interface OrderProcessorRepository {
    
    /**
     * Creates a new product.
     * 
     * @param product The product to be created.
     * @throws ProductIdAlreadyExistsException If the product ID already exists.
     */
    boolean createProduct(Product product) throws ProductIdAlreadyExistsException;
    
    /**
     * Creates a new customer.
     * 
     * @param customer The customer to be created.
     * @throws CustomerAlreadyExistsException If the customer already exists.
     */
    boolean createCustomer(Customer customer) throws CustomerAlreadyExistsException;
    
    /**
     * Deletes a customer.
     * 
     * @param customerId The ID of the customer to be deleted.
     * @return true if the customer is successfully deleted, false otherwise.
     */
    boolean deleteCustomer(int customerId);
    
    /**
     * Removes a product from the cart.
     * 
     * @param customer The customer whose cart is being modified.
     * @param product The product to be removed from the cart.
     * @throws CustomerNotFoundException If the customer is not found.
     * @throws ProductNotFoundException If the product is not found.
     */
    boolean removeFromCart(Customer customer, Product product) throws CustomerNotFoundException, ProductNotFoundException;
    
    /**
     * Retrieves orders for a specific customer.
     * 
     * @param customerId The ID of the customer.
     * @return A list of orders mapped to their respective products and quantities.
     * @throws CustomerNotFoundException If the customer is not found.
     */
    List<Map<Product, Integer>> getOrdersByCustomer(int customerId) throws CustomerNotFoundException;
    
    /**
     * Retrieves the products in a customer's cart.
     * 
     * @param customer The customer whose cart is being viewed.
     * @return A list of products in the customer's cart.
     * @throws CustomerNotFoundException If the customer is not found.
     */
    List<Product> viewCart(Customer customer) throws CustomerNotFoundException;
    
    /**
     * Places an order for a customer.
     * 
     * @param customerId The ID of the customer placing the order.
     * @param productsAndQuantities The products and their quantities to be ordered.
     * @param shippingAddress The shipping address for the order.
     * @return true if the order is successfully placed, false otherwise.
     * @throws CustomerNotFoundException If the customer is not found.
     * @throws ProductNotFoundException If a product in the order is not found.
     */
    
    List<Map<Product, Integer>> viewCustomerOrders(int customerId) throws OrderNotFoundException, CustomerNotFoundException;
    
    /**
     * Adds a product to a customer's cart.
     * 
     * @param cartId The ID of the cart.
     * @param customerId The ID of the customer.
     * @param productId The ID of the product to be added.
     * @param quantity The quantity of the product to be added.
     * @return true if the product is successfully added to the cart, false otherwise.
     * @throws CustomerNotFoundException If the customer is not found.
     */
    boolean addToCart(int cartId, int customerId, int productId, int quantity) throws CustomerNotFoundException;

	
   
    /**
     * Deletes a product.
     * 
     * @param productId The ID of the product to be deleted.
     * @return true if the product is successfully deleted, false otherwise.
     * @throws ProductNotFoundException If the product is not found.
     */
    boolean deleteProduct(int productId) throws ProductNotFoundException;
    
    /**
     * Retrieves all products.
     * 
     * @return A list of all products.
     */
    List<Product> viewAllProducts();
    
    /**
     * Retrieves a product by its ID.
     * 
     * @param productId The ID of the product.
     * @return The product with the specified ID.
     * @throws ProductNotFoundException If the product is not found.
     */
    Product getProductById(int productId) throws ProductNotFoundException;
    
    /**
     * Retrieves a customer by their ID.
     * 
     * @param customerId The ID of the customer.
     * @return The customer with the specified ID.
     * @throws CustomerNotFoundException If the customer is not found.
     */
    Customer getCustomerById(int customerId) throws CustomerNotFoundException;

	/**
	 * Places an order for a customer with the specified products and quantities to the provided shipping address.
	 *
	 * @param customerId           The ID of the customer placing the order.
	 * @param productsAndQuantities A list containing maps of products and their corresponding quantities.
	 * @param shippingAddress      The shipping address for the order.
	 * @return 
	 * @return true if the order is successfully placed, false otherwise.
	 * @throws CustomerNotFoundException If the customer with the given ID is not found.
	 * @throws ProductNotFoundException  If a product in the order is not found.
	 */
 boolean placeOrder(int customerId, List<Map<Product, Integer>> productsAndQuantities, String shippingAddress)
            throws CustomerNotFoundException, ProductNotFoundException;
    /**
     * Retrieves all orders for a specific customer.
     * 
     * @param customerId The ID of the customer.
     * @return A list of orders mapped to their respective products and quantities.
     * @throws OrderNotFoundException If no orders are found for the customer.
     * @throws CustomerNotFoundException If the customer is not found.
     */

	}
