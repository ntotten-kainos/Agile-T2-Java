package com.kainos.ea.controller;

import com.kainos.ea.exception.BankNumberLengthException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.NinLengthException;
import com.kainos.ea.exception.SalaryTooLowException;
import com.kainos.ea.model.EmployeeRequest;
import com.kainos.ea.service.EmployeeService;
import com.kainos.ea.service.SalesEmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HRTest {
    EmployeeService employeeService = Mockito.mock(EmployeeService.class);
    SalesEmployeeService salesEmployeeService = Mockito.mock(
            SalesEmployeeService.class);

    private final HR hr = new HR(employeeService, salesEmployeeService);

    private final EmployeeRequest employeeRequest = new EmployeeRequest(
            30000,
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
            "AA1A11AA1"
    );

    @Test
    void createEmployee_shouldReturnId_whenInsertSuccessful() throws DatabaseConnectionException, SQLException,
            BankNumberLengthException, SalaryTooLowException, NinLengthException {
        int expectedId = 1;

        when(employeeService.insertEmployee(employeeRequest)).thenReturn(expectedId);

        Response response = hr.createEmployee(employeeRequest);

        assertEquals(201, response.getStatus());
        assertEquals(expectedId, (int) response.getEntity());
    }

    /*
    Additional Exercise 1

    Write a unit test for the createEmployee method

    When the service throws a SQLException

    Expect 500 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 2

    Write a unit test for the createEmployee method

    When the service throws a DatabaseConnectionException

    Expect 500 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 3

    Write a unit test for the createEmployee method

    When the service throws a BankNumberLengthException

    Expect 400 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 4

    Write a unit test for the createEmployee method

    When the service throws a SalaryTooLowException

    Expect 400 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 5

    Write a unit test for the getEmployeeById method

    When the service returns an employee

    Expect employee to be returned in response

    This should pass without code changes
     */

    /*
    Additional Exercise 6

    Write a unit test for the getEmployeeById method

    When the service throws SQLException

    Expect 500 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 7

    Write a unit test for the getEmployeeById method

    When the service throws SalaryTooLowException

    Expect 500 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 8

    Write a unit test for the getEmployeeById method

    When the service throws DoesNotExistException

    Expect 404 to be returned

    This should pass without code changes
     */

     /*
    Additional Exercise 9

    Write a unit test for the getEmployees method

    When the service returns an employee list

    Expect employee list to be returned in response

    This should pass without code changes
     */

    /*
    Additional Exercise 10

    Write a unit test for the getEmployees method

    When the service throws SQLException

    Expect 500 to be returned

    This should pass without code changes
     */

    /*
    Additional Exercise 11

    Write a unit test for the getEmployees method

    When the service throws SalaryTooLowException

    Expect 500 to be returned

    This should pass without code changes
     */

}