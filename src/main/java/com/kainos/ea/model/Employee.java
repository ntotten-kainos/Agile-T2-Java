package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Employee implements Comparable<Employee>, Serializable {
    private int employeeId;
    private float salary;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String address2;
    private String city;
    private String county;
    private String postalCode;
    private String country;
    private String phoneNo;
    private String bankNo;
    private String nin;

    public static final int MIN_SALARY = 7_000;

    public Employee(int employeeId) {
        setEmployeeId(employeeId);
    }

    public float getSalary() {
        return this.salary;
    }

    public void setSalary(float salary) {
        if(salary >= MIN_SALARY) {
            this.salary = salary;
        }
    }

    @JsonCreator
    public Employee(
            @JsonProperty("salary") float salary,
            @JsonProperty("fname") String fname,
            @JsonProperty("lname") String lname,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("address2") String address2,
            @JsonProperty("city") String city,
            @JsonProperty("county") String county,
            @JsonProperty("postalCode") String postalCode,
            @JsonProperty("country") String country,
            @JsonProperty("phoneNo") String phoneNo,
            @JsonProperty("bankNo") String bankNo,
            @JsonProperty("nin") String nin) {
        this.setSalary(salary);
        this.setFname(fname);
        this.setLname(lname);
        this.setEmail(email);
        this.setAddress(address);
        this.setAddress2(address2);
        this.setCity(city);
        this.setCounty(county);
        this.setPostalCode(postalCode);
        this.setCountry(country);
        this.setPhoneNo(phoneNo);
        this.setBankNo(bankNo);
        this.setNin(nin);
    }

    public float calcPay(){
        return getSalary() / 12;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Employee){
            Employee emp = (Employee) obj;
            return this.getEmployeeId() == emp.getEmployeeId()
                    && this.getSalary() == emp.getSalary();
        } else return false;
    }

    @Override
    public String toString() {
        return String.format("Employee %d: %s, £%,.2f. "
                        + "Monthly gross pay: £%,.2f.",
                this.getEmployeeId(), this.getLname(), this.getSalary()/100.0, this.calcPay()/100.0);
    }

    @Override
    public int compareTo(Employee o) {
        return getSalary() > o.getSalary() ? 1 : -1;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }
}
