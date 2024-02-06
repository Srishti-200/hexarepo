package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.entity.Products;
import com.hexaware.entity.User;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.UserNotFoundException;
import com.hexaware.util.DBUtil;

public class OrderManagementRepositoryImpl implements IOrderManagementRepository {

    private Connection connection;
    public OrderManagementRepositoryImpl() {
        this.connection = DBUtil.getDBConn();
    }

    @Override
    public void createOrder(int userId, int productId, int orderId, Date orderDate) throws SQLException,UserNotFoundException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Orders (order_id, userId, productId, order_date) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setInt(3, productId);
            statement.setDate(4, orderDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating order", e);
        }
    }

@Override
    public void cancelOrder( int order_Id) throws SQLException, OrderNotFoundException {
       
        if (!orderExists(order_Id)) {
            throw new OrderNotFoundException("Order not found");
        }
        String deleteOrderQuery = "DELETE FROM Orders WHERE order_Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderQuery)) {
            statement.setInt(1, order_Id);
            statement.executeUpdate();
        }
    }
    private boolean orderExists( int orderId) throws SQLException {
        String selectOrderQuery = "SELECT * FROM Orders WHERE order_Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectOrderQuery)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
    @Override
    public void createProduct(User user, Products product) throws SQLException{
      
        String insertProductQuery = "INSERT INTO Products (productId, productName, description, price, quantityInStock, type) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertProductQuery)) {
            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantityInStock());
            statement.setString(6, product.getType());
            statement.executeUpdate();
        }
    }

    @Override
    public void createUser(User user) throws SQLException {
        String insertUserQuery = "INSERT INTO User (userId, username, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUserQuery)) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Products> getAllProducts() throws SQLException {
        List<Products> products = new ArrayList<>();
      
        String selectProductsQuery = "SELECT * FROM Products";
        try (PreparedStatement statement = connection.prepareStatement(selectProductsQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products(
                        resultSet.getInt("productId"),
                        resultSet.getString("productName"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getString("type")
                );
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Products> getOrderByUser(User user) throws SQLException {
        List<Products> orderedProducts = new ArrayList<>();
        String selectOrderedProductsQuery = "SELECT p.* FROM Products p INNER JOIN Orders o ON p.productId = o.productId WHERE o.userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectOrderedProductsQuery)) {
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products(
                        resultSet.getInt("productId"),
                        resultSet.getString("productName"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantityInStock"),
                        resultSet.getString("type")
                );
                orderedProducts.add(product);
            }
        }
        return orderedProducts;
    }
    public boolean userExists(int userId) throws SQLException {
        String selectUserQuery = "SELECT * FROM User WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectUserQuery)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
	
}
