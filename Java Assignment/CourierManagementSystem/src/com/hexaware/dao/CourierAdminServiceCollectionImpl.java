package com.hexaware.dao;           // task 8.3
 
import com.hexaware.entity.CourierCompany;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceImpl implements ICourierAdminService {
    public CourierAdminServiceCollectionImpl(CourierCompany companyObj) {
        super(companyObj);
    }

	@Override
	public int addCourierStaff(String name, String contactNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

    
}
