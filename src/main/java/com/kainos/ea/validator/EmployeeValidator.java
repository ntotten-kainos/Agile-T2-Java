package com.kainos.ea.validator;

import com.kainos.ea.exception.BankNumberLengthException;
import com.kainos.ea.exception.NinLengthException;
import com.kainos.ea.exception.SalaryTooLowException;
import com.kainos.ea.model.EmployeeRequest;

public class EmployeeValidator {
    public boolean isValidEmployee(EmployeeRequest employeeRequest) throws SalaryTooLowException, BankNumberLengthException, NinLengthException {
        if (employeeRequest.getSalary() < 20000) {
            throw new SalaryTooLowException();
        }

        if (employeeRequest.getBankNo().length() != 8) {
            throw new BankNumberLengthException();
        }

        if (employeeRequest.getFname().length() > 50) {
            return false;
        }

        if (employeeRequest.getLname().length() > 50) {
            return false;
        }

        if (employeeRequest.getNin().length() != 9) {
            throw new NinLengthException();
        }

        return true;
    }
}
