package com.hexaware.entity;

public class Customer {
	private int customerId;
    private String name;
    private String email;
    private String password;

    // Parameterized constructor
    /**
     * Customer details
     * 
     * @param customerId
     * @param name
     * @param email
     * @param password
     */
    public Customer(int customerId, String name, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /**
     * @param customerId
     */
    public Customer(int customerId) {
        this.customerId = customerId;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    /**
     * @return customer name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return customer email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return customer password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
