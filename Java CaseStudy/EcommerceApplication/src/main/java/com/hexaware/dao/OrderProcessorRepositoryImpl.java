 package com.hexaware.dao;

import com.hexaware.exception.CustomerNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerAlreadyExistsException;
import com.hexaware.exception.ProductIdAlreadyExistsException;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBConnUtil;

/**
 * The OrderProcessorRepositoryImpl class implements methods to interact with the database
 * for processing orders, managing customers, and products.
 */
public class OrderProcessorRepositoryImpl implements OrderProcessorRepository {

    /**
     * Creates a new product in the database.
     *
     * @param product The product to be created.
     * @return true if the product is created successfully, false otherwise.
     * @throws ProductIdAlreadyExistsException If the product ID already exists in the database.
     */
    @Override
    public boolean createProduct(Product product) throws ProductIdAlreadyExistsException {
        if (productExists(product.getProductId())) {
            throw new ProductIdAlreadyExistsException("Product with ID " + product.getProductId() + " already exists.");
        }

        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO products (product_id, prod_name, prod_price, prod_desp, stock_quantity) VALUES (?, ?,?,?,?)")) {
            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getStockQuantity());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a new customer in the database.
     *
     * @param customer The customer to be created.
     * @return true if the customer is created successfully, false otherwise.
     * @throws CustomerAlreadyExistsException If the customer ID already exists in the database.
     */
    @Override
    public boolean createCustomer(Customer customer) throws CustomerAlreadyExistsException {
        if (customerExists(customer.getCustomerId())) {
            throw new CustomerAlreadyExistsException("Customer with ID " + customer.getCustomerId() + " already exists.");
        }

        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO customers (customer_id, cust_name, cust_email, password) VALUES (?, ?,?,?)")) {
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether a customer with the given ID exists in the database.
     *
     * @param customerId The ID of the customer to check.
     * @return true if the customer exists, false otherwise.
     */
    private boolean customerExists(int customerId) {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?")) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the list of orders associated with the given customer ID.
     *
     * @param customerId The ID of the customer.
     * @return A list of maps containing product details and quantities.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    @Override
    public List<Map<Product, Integer>> viewCustomerOrders(int customerId) throws CustomerNotFoundException {
        List<Map<Product, Integer>> customerOrders = new ArrayList<>();
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT orders.order_id, orders.order_date, orders.total_price, orders.shipping_address, " +
                             "orderItems.product_id, orderItems.quantity, products.prod_name, products.prod_price, products.prod_desp, products.stock_quantity " +
                             "FROM orders " +
                             "JOIN orderItems ON orders.order_id = orderItems.order_id " +
                             "JOIN products ON orderItems.product_id = products.product_id " +
                             "WHERE orders.customer_id = ?")) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
                }

                resultSet.beforeFirst(); 

                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    String orderDate = resultSet.getString("order_date");
                    int totalPrice = resultSet.getInt("total_price");
                    String shippingAddress = resultSet.getString("shipping_address");
                    int productId = resultSet.getInt("product_id");
                    int quantity = resultSet.getInt("quantity");
                    String productName = resultSet.getString("prod_name");
                    double productPrice = resultSet.getDouble("prod_price");
                    String productDescription = resultSet.getString("prod_desp");
                    int stockQuantity = resultSet.getInt("stock_quantity");

                    Product product = new Product(productId, productName, productPrice, productDescription, stockQuantity, stockQuantity);
                    Map<Product, Integer> orderDetails = new HashMap<>();
                    orderDetails.put(product, quantity);

                    customerOrders.add(orderDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerOrders;
    }
    /**
     * Places an order for a customer.
     * 
     * @param customerId           The ID of the customer placing the order.
     * @param orderId              The ID of the order. If 0, it's a new order; otherwise, it's an existing order.
     * @param productsAndQuantities A list containing maps of products and their corresponding quantities.
     * @param shippingAddress      The shipping address for the order.
     * @return true if the order was successfully placed, otherwise false.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     * @throws ProductNotFoundException  If a product in the order is not found.
     * */
    public boolean placeOrder(int customerId, List<Map<Product, Integer>> productsAndQuantities, String shippingAddress)
            throws CustomerNotFoundException, ProductNotFoundException {
        // Check if customer exists
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }

        try (Connection connection = DBConnUtil.getConnection()) {
            // Insert order into orders table
            try (PreparedStatement orderStatement = connection.prepareStatement(
                    "INSERT INTO orders (customer_id, order_date, total_price, shipping_address) VALUES (?, CURRENT_DATE, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                orderStatement.setInt(1, customerId);
                int totalPrice = calculateTotalPrice(productsAndQuantities);
                orderStatement.setInt(2, totalPrice);
                orderStatement.setString(3, shippingAddress);

                int rowsAffected = orderStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Retrieve generated order ID
                    try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int orderId = generatedKeys.getInt(1);
                            // Insert or update order items into orderitems table
                            for (Map<Product, Integer> item : productsAndQuantities) {
                                for (Map.Entry<Product, Integer> entry : item.entrySet()) {
                                    Product product = entry.getKey();
                                    int quantity = entry.getValue();

                                    // Check if the order item already exists for the given order and product
                                    boolean orderItemExists = false;
                                    try (PreparedStatement checkExistingStatement = connection.prepareStatement(
                                            "SELECT * FROM orderitems WHERE order_id = ? AND product_id = ?")) {
                                        checkExistingStatement.setInt(1, orderId);
                                        checkExistingStatement.setInt(2, product.getProductId());
                                        try (ResultSet existingResultSet = checkExistingStatement.executeQuery()) {
                                            orderItemExists = existingResultSet.next();
                                        }
                                    }

                                    if (orderItemExists) {
                                        // Update the quantity if the order item already exists
                                        try (PreparedStatement updateStatement = connection.prepareStatement(
                                                "UPDATE orderitems SET quantity = quantity + ? WHERE order_id = ? AND product_id = ?")) {
                                            updateStatement.setInt(1, quantity);
                                            updateStatement.setInt(2, orderId);
                                            updateStatement.setInt(3, product.getProductId());
                                            updateStatement.executeUpdate();
                                        }
                                    } else {
                                        // Insert the order item if it doesn't already exist
                                        try (PreparedStatement orderItemsStatement = connection.prepareStatement(
                                                "INSERT INTO orderitems (order_id, product_id, quantity) VALUES (?, ?, ?)")) {
                                            orderItemsStatement.setInt(1, orderId);
                                            orderItemsStatement.setInt(2, product.getProductId());
                                            orderItemsStatement.setInt(3, quantity);
                                            orderItemsStatement.executeUpdate();
                                        }
                                    }
                                }
                            }
                            return true; // Order placed successfully
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exception
        }
        return false; // Failed to place order
    }


    private void updateCartAfterOrder(Connection connection, int customerId, List<Map<Product, Integer>> productsAndQuantities) throws SQLException {
        for (Map<Product, Integer> productAndQuantity : productsAndQuantities) {
            for (Map.Entry<Product, Integer> entry : productAndQuantity.entrySet()) {
                Product product = entry.getKey();
                int quantityOrdered = entry.getValue();

                // Check if the product is in the customer's cart
                if (isProductInCart(connection, customerId, product.getProductId())) {
                            int cartQuantity = getCartQuantity(connection, customerId, product.getProductId());
                    if (quantityOrdered == cartQuantity) {
                        deleteCartItem(connection, customerId, product.getProductId());
                    } else if (quantityOrdered < cartQuantity) {
                        updateCartQuantity(connection, customerId, product.getProductId(), cartQuantity - quantityOrdered);
                    } else {
                        System.out.println("You are ordering more quantity than what's in your cart for product: " + product.getName());
                    }
                } else {
                    // If the product is not in the cart, print a message
                    System.out.println("The product: " + product.getName() + " is not in your cart.");
                }
            }
        }
    }

    private boolean isProductInCart(Connection connection, int customerId, int productId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM cart WHERE customer_id = ? AND product_id = ?")) {
            statement.setInt(1, customerId);
            statement.setInt(2, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private int getCartQuantity(Connection connection, int customerId, int productId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT quantity FROM cart WHERE customer_id = ? AND product_id = ?")) {
            statement.setInt(1, customerId);
            statement.setInt(2, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("quantity");
                }
            }
        }
        return 0;
    }

    private void deleteCartItem(Connection connection, int customerId, int productId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM cart WHERE customer_id = ? AND product_id = ?")) {
            statement.setInt(1, customerId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    private void updateCartQuantity(Connection connection, int customerId, int productId, int newQuantity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE cart SET quantity = ? WHERE customer_id = ? AND product_id = ?")) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, customerId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        }
    }

    private int calculateTotalPrice(List<Map<Product, Integer>> productsAndQuantities) {
        int totalPrice = 0;
        for (Map<Product, Integer> productAndQuantity : productsAndQuantities) {
            for (Map.Entry<Product, Integer> entry : productAndQuantity.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                totalPrice += product.getPrice() * quantity;
            }
        }
        return totalPrice;
    }
    /**
     * Updates the order with new quantities.
     * 
     * @param connection           The database connection.
     * @param orderId              The ID of the order to update.
     * @param productsAndQuantities A list containing maps of products and their corresponding quantities.
     * @throws SQLException If a database error occurs.
     */
    private void updateOrder(Connection connection, int orderId, List<Map<Product, Integer>> productsAndQuantities) throws SQLException {
        try (PreparedStatement deleteStatement = connection.prepareStatement(
                "DELETE FROM orderItems WHERE order_id = ?")) {
            deleteStatement.setInt(1, orderId);
            deleteStatement.executeUpdate();
        }

        try (PreparedStatement orderItemsStatement = connection.prepareStatement(
                "INSERT INTO orderItems (order_id, product_id, quantity) VALUES (?, ?, ?)")) {
            for (Map<Product, Integer> productAndQuantity : productsAndQuantities) {
                for (Map.Entry<Product, Integer> entry : productAndQuantity.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();

                    orderItemsStatement.setInt(1, orderId);
                    orderItemsStatement.setInt(2, product.getProductId());
                    orderItemsStatement.setInt(3, quantity);
                    orderItemsStatement.addBatch(); // Add batch for efficient execution
                }
            }
            orderItemsStatement.executeBatch(); // Execute the batch
        }
    }



public void viewOrder(int orderId) throws SQLException {
    try (Connection connection = DBConnUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(
                 "SELECT * FROM orders WHERE order_id = ?")) {
        statement.setInt(1, orderId);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve and display order details
                int customerId = resultSet.getInt("customer_id");
                Date orderDate = resultSet.getDate("order_date");
                int totalPrice = resultSet.getInt("total_price");
                String shippingAddress = resultSet.getString("shipping_address");

                System.out.println("Order ID: " + orderId);
                System.out.println("Customer ID: " + customerId);
                System.out.println("Order Date: " + orderDate);
                System.out.println("Total Price: " + totalPrice);
                System.out.println("Shipping Address: " + shippingAddress);

            }
        }
    }
}
                    		
    /**
     * Retrieves the products in the customer's cart.
     *
     * @param customer The customer whose cart is to be viewed.
     * @return A list of products in the customer's cart.
     * @throws CustomerNotFoundException If the customer is not found in the database.
     */
    @Override
    public List<Product> viewCart(Customer customer) throws CustomerNotFoundException {
        List<Product> cartProducts = new ArrayList<>();
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT products.*, cart.quantity AS cart_quantity " +
                             "FROM cart " +
                             "JOIN products ON cart.product_id = products.product_id " +
                             "WHERE cart.customer_id = ?")) {
            statement.setInt(1, customer.getCustomerId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    // No rows found, customer not found in the database
                    throw new CustomerNotFoundException("Customer with ID " + customer.getCustomerId() + " not found");
                }
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("prod_name");
                    double productPrice = resultSet.getDouble("prod_price");
                    String productDescription = resultSet.getString("prod_desp");
                    int stockQuantity = resultSet.getInt("stock_quantity");
                    int cartQuantity = resultSet.getInt("cart_quantity");

                    Product product = new Product(productId, productName, productPrice, productDescription, stockQuantity, cartQuantity);
                    cartProducts.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartProducts;
    }


    /**
     * Checks whether a product with the given ID exists in the database.
     *
     * @param productId The ID of the product to check.
     * @return true if the product exists, false otherwise.
     */
    private boolean productExists(int productId) {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?")) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a customer from the database.
     *
     * @param customerId The ID of the customer to be deleted.
     * @return true if the customer is successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteCustomer(int customerId) {
        try (Connection connection = DBConnUtil.getConnection()) {
            // Delete related records in the orderitems table
            deleteOrderItems(connection, customerId);

            // Delete related records in the orders table
            deleteOrders(connection, customerId);

            // Delete related records in the cart table
            deleteCartRecords(connection, customerId);

            // Now, delete the customer
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?")) {
                statement.setInt(1, customerId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void deleteOrderItems(Connection connection, int customerId) throws SQLException {
        // Select orders for the given customer
        List<Integer> orderIds = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT order_id FROM orders WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderIds.add(resultSet.getInt("order_id"));
                }
            }
        }

        // Delete order items for each order
        for (int orderId : orderIds) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM orderitems WHERE order_id = ?")) {
                statement.setInt(1, orderId);
                statement.executeUpdate();
            }
        }
    }

    private void deleteOrders(Connection connection, int customerId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        }
    }

    private void deleteCartRecords(Connection connection, int customerId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM cart WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        }
    }


    /**
     * Removes a product from the customer's cart.
     *
     * @param customer The customer from whose cart the product is to be removed.
     * @param product  The product to be removed from the cart.
     * @return true if the product is successfully removed from the cart, false otherwise.
     * @throws ProductNotFoundException If the product is not found in the cart.
     */
    public static void displayCart(int customerId) {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT product_id, quantity FROM cart WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                System.out.println("Product ID: " + productId + ", Quantity: " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	@Override
	public boolean removeFromCart(Customer customer, Product product)
	        throws CustomerNotFoundException, ProductNotFoundException {
	    try (Connection connection = DBConnUtil.getConnection();
	         PreparedStatement statement = connection.prepareStatement(
	                 "DELETE FROM cart WHERE customer_id = ? AND product_id = ?")) {
	        statement.setInt(1, customer.getCustomerId());
	        statement.setInt(2, product.getProductId());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    }

    /**
     * Retrieves the orders made by a specific customer.
     *
     * @param customerId The ID of the customer whose orders are to be retrieved.
     * @return A list of maps containing product details and their respective quantities in each order.
     * @throws CustomerNotFoundException If the customer is not found in the database.
     */
    @Override
    public List<Map<Product, Integer>> getOrdersByCustomer(int customerId) throws CustomerNotFoundException {
        List<Map<Product, Integer>> customerOrders = new ArrayList<>();
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT orders.order_id, orders.order_date, orders.shipping_address, " +
                             "orderItems.product_id, SUM(orderItems.quantity) AS total_quantity, " +
                             "products.prod_name, products.prod_price, products.prod_desp, products.stock_quantity " +
                             "FROM orders " +
                             "JOIN orderItems ON orders.order_id = orderItems.order_id " +
                             "JOIN products ON orderItems.product_id = products.product_id " +
                             "WHERE orders.customer_id = ? " +
                             "GROUP BY orders.order_id, orderItems.product_id")) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    // No rows found, customer not found in the database
                    throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
                }
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    String orderDate = resultSet.getString("order_date");
                    String shippingAddress = resultSet.getString("shipping_address");
                    int productId = resultSet.getInt("product_id");
                    int totalQuantity = resultSet.getInt("total_quantity");
                    String productName = resultSet.getString("prod_name");
                    double productPrice = resultSet.getDouble("prod_price");
                    String productDescription = resultSet.getString("prod_desp");
                    int stockQuantity = resultSet.getInt("stock_quantity");

                    Product product = new Product(productId, productName, productPrice, productDescription, stockQuantity, stockQuantity);
                    Map<Product, Integer> orderDetails = new HashMap<>();
                    orderDetails.put(product, totalQuantity);

                    customerOrders.add(orderDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerOrders;
    }
    /**
     * Deletes a product from the database.
     *
     * @param productId The ID of the product to be deleted.
     * @return true if the product is successfully deleted, false otherwise.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
    @Override
    public boolean deleteProduct(int productId) throws ProductNotFoundException {

        if (!isProductExists(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }

        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?")) {
            statement.setInt(1, productId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether a customer with the given ID exists in the database.
     *
     * @param customerId The ID of the customer to check.
     * @return true if the customer exists, false otherwise.
     */
    public boolean isCustomerExists(int customerId) {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?")) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether a product with the given ID exists in the database.
     *
     * @param productId The ID of the product to check.
     * @return true if the product exists, false otherwise.
     */
    public boolean isProductExists(int productId) {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?")) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    /**
     * Adds products to the customer's cart.
     *
     * @param cartId     The ID of the cart.
     * @param customerId The ID of the customer.
     * @param productId  The ID of the product to add to the cart.
     * @param quantity   The quantity of the product to add to the cart.
     * @return true if the product is successfully added to the cart, false otherwise.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    public boolean addToCart(int cartId, int customerId, int productId, int quantity) throws CustomerNotFoundException {
        if (!isCustomerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }

        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO cart (cart_id, customer_id, product_id, Quantity) VALUES (?, ?, ?,?)")) {
            statement.setInt(1, cartId);
            statement.setInt(2, customerId);
            statement.setInt(3, productId);
            statement.setInt(4, quantity);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Retrieves a list of all products available in the database.
     *
     * @return A list containing all products.
     */
    @Override
    public List<Product> viewAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("prod_name");
                    double productPrice = resultSet.getDouble("prod_price");
                    String productDescription = resultSet.getString("prod_desp");
                    int stockQuantity = resultSet.getInt("stock_quantity");

                    Product product = new Product(productId, productName, productPrice, productDescription, stockQuantity, stockQuantity);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Retrieves a product from the database based on the provided product ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The product corresponding to the given ID.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
    @Override
    public Product getProductById(int productId) throws ProductNotFoundException {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?")) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("prod_name");
                    double price = resultSet.getDouble("prod_price");
                    String description = resultSet.getString("prod_desp");
                    int stockQuantity = resultSet.getInt("stock_quantity");
                    return new Product(productId, name, price, description, stockQuantity, stockQuantity);
                } else {
                    throw new ProductNotFoundException("Product with ID " + productId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a customer from the database based on the provided customer ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The customer corresponding to the given ID.
     * @throws CustomerNotFoundException If the customer with the given ID is not found.
     */
    @Override
    public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("customer_id");
                    String name = resultSet.getString("cust_name");
                    String email = resultSet.getString("cust_email");
                    String password = resultSet.getString("password");
                    return new Customer(id, name, email, password);
                } else {
                    throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


	

	

}


