package com.hexaware.model;

import java.util.Objects;

public class Employee {
    private int empno;
    private String ename;
    private Salary sal;

    public Employee() {
        // Default constructor
    }

    public Employee(int empno, String ename, Salary sal) {
        this.empno = empno;
        this.ename = ename;
        this.sal = sal;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Salary getSal() {
        return sal;
    }

    public void setSal(Salary sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Employee [empno=" + empno + ", ename=" + ename + ", sal=" + sal + "]";
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return empno == employee.empno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno);
    }
}
