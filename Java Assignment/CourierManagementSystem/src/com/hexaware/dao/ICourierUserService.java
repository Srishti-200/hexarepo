package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Courier;

public interface ICourierUserService {
	String placeOrder(Courier courierObj);
    String getOrderStatus(String trackingNumber);
    boolean cancelOrder(String trackingNumber);
    List<Courier> getAssignedOrder(int courierStaffId);
}
