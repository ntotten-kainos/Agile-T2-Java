package com.kainos.ea.validator;

import com.kainos.ea.exception.BankNumberLengthException;
import com.kainos.ea.exception.SalaryTooLowException;
import com.kainos.ea.model.EmployeeRequest;

public class EmployeeValidator {
    public boolean isValidEmployee(EmployeeRequest employeeRequest) throws SalaryTooLowException, BankNumberLengthException {
        if (employeeRequest.getSalary() < 20000) {
            throw new SalaryTooLowException();
        }

        if (employeeRequest.getBankNo().length() != 8) {
            throw new BankNumberLengthException();
        }

        return true;
    }
}
