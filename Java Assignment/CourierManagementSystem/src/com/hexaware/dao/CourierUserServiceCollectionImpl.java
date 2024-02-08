package com.hexaware.dao;                    // task8.2 courieruserservicecollectionn

import com.hexaware.entity.Courier;
import com.hexaware.entity.CourierCompanyCollectionTask8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourierUserServiceCollectionImpl implements ICourierUserService {
    private CourierCompanyCollectionTask8 companyObj;

    public CourierUserServiceCollectionImpl(CourierCompanyCollectionTask8 companyObj) {
        this.companyObj = companyObj;
    }

    @Override
    public String placeOrder(Courier courierObj) {
        companyObj.addCourier(courierObj);
        return courierObj.getTrackingNumber();
    }

    @Override
    public String getOrderStatus(String trackingNumber) {
        Optional<Courier> foundCourier = companyObj.getCourierList().stream()
                .filter(c -> c.getTrackingNumber().equals(trackingNumber))
                .findFirst();
        return foundCourier.map(Courier::getStatus).orElse("Order not found");
    }

    @Override
    public boolean cancelOrder(String trackingNumber) {
        return companyObj.getCourierList().removeIf(c -> c.getTrackingNumber().equals(trackingNumber));
    }

    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) {
        return companyObj.getCourierList().stream()
                .filter(c -> c.getCourierStaffId() == courierStaffId)
                .collect(Collectors.toList());
    }
}
