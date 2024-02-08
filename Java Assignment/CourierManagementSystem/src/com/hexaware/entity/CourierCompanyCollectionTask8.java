package com.hexaware.entity;                       // Task 8.1  couriercompanycollection

import java.util.ArrayList;
import java.util.List;

public class CourierCompanyCollectionTask8 {
	private List<Courier> courierList;

    public CourierCompanyCollectionTask8() {
        this.courierList = new ArrayList<>();
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public void addCourier(Courier courier) {
        courierList.add(courier);
    }
}
