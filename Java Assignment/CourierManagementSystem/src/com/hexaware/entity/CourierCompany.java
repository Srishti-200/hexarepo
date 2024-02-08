package com.hexaware.entity;            // 5.5: POJO

import java.util.Date;

public class CourierCompany {
	private int courierID;
	 private String userName;
	private String senderAddress;
	private String 	receiverName;
	private String receiverAddress; 
	private double weight;
	private String status;
	private int trackingNumber;
	private Date deliveryDate;
	
	public CourierCompany()
	 {
	 
	 }
		 public CourierCompany(int courierID,String userName,String senderAddress ,String 	receiverName,String receiverAddress,
				 double weight,String status, int trackingNumber,Date deliveryDate )
		 {
			 this.courierID=courierID;
			 this.deliveryDate=deliveryDate;
			 this.receiverAddress=receiverAddress;
			 this.receiverName =receiverName;
			 this.senderAddress= senderAddress;
			 this.status = status;
			 this.trackingNumber  = trackingNumber;
			 this.weight  =weight;
		 }
	
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(int trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	private int userId;
	public int getCourierID() {
		return courierID;
	}
	public void setCourierID(int courierID) {
		this.courierID = courierID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "CourierCompany [courierID=" + courierID + ", userName=" + userName + ", senderAddress=" + senderAddress
				+ ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", weight=" + weight
				+ ", status=" + status + ", trackingNumber=" + trackingNumber + ", deliveryDate=" + deliveryDate
				+ ", userId=" + userId + "]";
	}
}
