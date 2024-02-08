package com.hexaware.dao;        //task 8.2

import com.hexaware.entity.CourierCompany;

public class CourierAdminServiceImpl extends CourierUserServiceImpl implements ICourierAdminService {
    public CourierAdminServiceImpl(CourierCompany companyObj) {
        super(companyObj);
    }

	@Override
	public int addCourierStaff(String name, String contactNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

}
