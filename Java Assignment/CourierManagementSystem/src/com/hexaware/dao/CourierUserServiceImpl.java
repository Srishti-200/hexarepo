package com.hexaware.dao;          // task 8.1

import java.util.List;

import com.hexaware.entity.Courier;
import com.hexaware.entity.CourierCompany;

public class CourierUserServiceImpl implements ICourierUserService {
    protected CourierCompany companyObj;

    public CourierUserServiceImpl(CourierCompany companyObj) {
        this.companyObj = companyObj;
    }

	@Override
	public String placeOrder(Courier courierObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrderStatus(String trackingNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelOrder(String trackingNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Courier> getAssignedOrder(int courierStaffId) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
