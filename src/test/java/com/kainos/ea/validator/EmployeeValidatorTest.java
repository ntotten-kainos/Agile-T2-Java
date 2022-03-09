package com.kainos.ea.validator;

import com.kainos.ea.exception.BankNumberLengthException;
import com.kainos.ea.exception.SalaryTooLowException;
import com.kainos.ea.model.EmployeeRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidatorTest {

    EmployeeValidator employeeValidator = new EmployeeValidator();

    @Test
    public void isValidEmployee_shouldReturnTrue() throws SalaryTooLowException, BankNumberLengthException {
        EmployeeRequest employeeRequest = new EmployeeRequest(
                20000,
                "Tim",
                "Bloggs",
                "tbloggs@email.com",
                "1 Main Street",
                "Main Road",
                "Belfast",
                "Antrim",
                "BT99BT",
                "Northern Ireland",
                "12345678901",
                "12345678",
                "AA1A11AA"
        );

        assertTrue(employeeValidator.isValidEmployee(employeeRequest));
    }
}