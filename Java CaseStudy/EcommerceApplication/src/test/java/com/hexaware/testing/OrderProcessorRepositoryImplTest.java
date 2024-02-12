package com.hexaware.testing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.hexaware.dao.OrderProcessorRepositoryImpl;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.exception.ProductIdAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for testing the functionalities of the OrderProcessorRepositoryImpl class.
 */
public class OrderProcessorRepositoryImplTest {

    /**
     * Test method for creating a product.
     * 
     * @throws ProductIdAlreadyExistsException when the product id already exists.
     */
    @Test
    public void testCreateProduct_Success() throws ProductIdAlreadyExistsException {
        Product product = new Product(1191, "Test Product", 10.0, "Test Description", 100, 0);
        OrderProcessorRepositoryImpl repository = new OrderProcessorRepositoryImpl();

        boolean result = repository.createProduct(product);

        assertTrue(result);
    }

    /**
     * Test method for adding a product to the cart.
     * 
     * @throws CustomerNotFoundException when the customer is not found.
     */
    @Test
    public void testAddToCart_Success() throws CustomerNotFoundException {
        Customer customer = new Customer(1031, "Test Customer", "test@example.com", "password");
        Product product = new Product(103, "Test Product", 10.0, "Test Description", 100, 0);
        OrderProcessorRepositoryImpl repository = new OrderProcessorRepositoryImpl();

        boolean result = repository.addToCart(1, customer.getCustomerId(), product.getProductId(), 1);

        assertTrue(result);
    }

    /**
     * Test method for placing an order.
     */
    @Test
    public void testPlaceOrder_Success() {
        Customer customer = new Customer(100, "Test Customer", "test@example.com", "password");
        Product product1 = new Product(111, "Fan", 2500.0, "High-speed fan", 25, 0);
        Product product2 = new Product(112, "Lamp", 500.0, "Desk lamp", 50, 0);
        List<Map<Product, Integer>> productsAndQuantities = new ArrayList<>();
        Map<Product, Integer> product1Map = new HashMap<>();
        Map<Product, Integer> product2Map = new HashMap<>();
        product1Map.put(product1, 2); // 2 units of Product 1
        product2Map.put(product2, 1); // 1 unit of Product 2
        productsAndQuantities.add(product1Map);
        productsAndQuantities.add(product2Map);
        String shippingAddress = "123 Test Street, Test City";
        OrderProcessorRepositoryImpl repository = new OrderProcessorRepositoryImpl();

        boolean result = false;
        try {
            // Since it's a new order, orderId is set to 0
            result = repository.placeOrder(customer.getCustomerId(), productsAndQuantities, shippingAddress);
        } catch (CustomerNotFoundException | ProductNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }

        assertTrue(result);
    }


    /**
     * Test method for checking if CustomerNotFoundException is thrown when customer is not found.
     * 
     * @throws CustomerNotFoundException when the customer is not found.
     */
    @Test(expected = CustomerNotFoundException.class)
    public void testException_CustomerNotFound() throws CustomerNotFoundException {
        OrderProcessorRepositoryImpl repository = new OrderProcessorRepositoryImpl();
        repository.getCustomerById(1002); 
    }

    /**
     * Test method for checking if ProductNotFoundException is thrown when product is not found.
     * 
     * @throws ProductNotFoundException when the product is not found.
     */
    @Test(expected = ProductNotFoundException.class)
    public void testException_ProductNotFound() throws ProductNotFoundException {
        OrderProcessorRepositoryImpl repository = new OrderProcessorRepositoryImpl();
        repository.getProductById(1002); 
    }
}
