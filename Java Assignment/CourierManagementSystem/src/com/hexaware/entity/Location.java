package com.hexaware.entity;                          // 5.4: POJO

public class Location {
	private int  LocationID;
	private String LocationName; 
    private String Address;
    
    public Location() {
    }
    public Location(int locationID, String locationName, String address) {
        this.LocationID = locationID;
        this.LocationName = locationName;
        this.Address = address;
        
    }
	@Override
	public String toString() {
		return "Location [LocationID=" + LocationID + ", LocationName=" + LocationName + ", Address=" + Address + "]";
	}
	public int getLocationID() {
		return LocationID;
	}
	public void setLocationID(int locationID) {
		LocationID = locationID;
	}
	public String getLocationName() {
		return LocationName;
	}
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
}
