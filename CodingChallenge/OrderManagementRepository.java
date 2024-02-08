package com.hexaware.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import com.hexaware.entity.Products;
import com.hexaware.entity.User;
import com.hexaware.exception.AdminNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.UserNotFoundException;

public interface OrderManagementRepository {
    void createProduct(User user, Products product) throws AdminNotFoundException, SQLException;
    void createUser(User user) throws SQLException;
    List<Products> getAllProducts() throws SQLException;
    List<Products> getOrderByUser(User user) throws SQLException;
	void cancelOrder(int order_Id) throws SQLException, OrderNotFoundException;
	void createOrder(int userId, int productId, int orderId, Date orderDate) throws SQLException, UserNotFoundException;
	
}
