package com.hexaware.entity;
public class Employee {
    private int empId;
    private String empName;
    private String empRole;
    private double empSal;

    public Employee() {
    }

    public Employee(int empId, String empName, String empRole, double empSal) {
        this.empId = empId;
        this.empName = empName;
        this.empRole = empRole;
        this.empSal = empSal;
    }
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public double getEmpSal() {
        return empSal;
    }

    public void setEmpSal(double empSal) {
        this.empSal = empSal;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empRole='" + empRole + '\'' +
                ", empSal=" + empSal +
                '}';
    }
}
