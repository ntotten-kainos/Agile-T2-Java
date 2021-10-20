package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployee extends Employee {
    private float commissionRate;
    private int salesTotal;

    public SalesEmployee(float commissionRate, int salesTotal, Employee employee) {
        super(employee.getSalary(), employee.getFname(), employee.getLname(), employee.getEmail(),
                employee.getAddress(), employee.getAddress2(), employee.getCity(), employee.getCounty(),
                employee.getPostalCode(), employee.getCountry(), employee.getPhoneNo(), employee.getBankNo(),
                employee.getNin());
        super.setEmployeeId(employee.getEmployeeId());
        this.setCommissionRate(commissionRate);
        this.setSalesTotal(salesTotal);
    }

    @JsonCreator
    public SalesEmployee(
            @JsonProperty("commissionRate") float commissionRate,
            @JsonProperty("salesTotal") int salesTotal,
            @JsonProperty("employeeId") int employeeId) {
        super(employeeId);
        this.setCommissionRate(commissionRate);
        this.setSalesTotal(salesTotal);
    }

    @Override
    public float calcPay(){ // calculate monthly pay in pence
        return super.calcPay() + Math.round(commissionRate * salesTotal);
    }

    public int getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(int salesTotal) {
        this.salesTotal = salesTotal;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof SalesEmployee){
            SalesEmployee sEmp = (SalesEmployee) obj;
            return super.equals(obj)
                    && this.getCommissionRate() == sEmp.getCommissionRate()
                    && this.getSalesTotal() == sEmp.getSalesTotal();
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Sales Employee %d: %,.2f%%. "
                        + "Monthly sales: £%d",
                this.getEmployeeId(), this.getCommissionRate(), this.getSalesTotal());
    }
}
