package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.*;
import com.kainos.ea.model.Employee;
import com.kainos.ea.model.EmployeeRequest;
import com.kainos.ea.model.SalesEmployee;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.EmployeeValidator;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    EmployeeDao employeeDao = Mockito.mock(EmployeeDao.class);
    EmployeeValidator employeeValidator = Mockito.mock(EmployeeValidator.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    EmployeeService employeeService = new EmployeeService(employeeDao, employeeValidator, databaseConnector);

    static EmployeeRequest employeeRequest = new EmployeeRequest(
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

    static List<Employee> employeeList = new ArrayList<>();

    Connection conn;

    @BeforeAll
    public static void initEmployeeList() {
        employeeList.clear();
        Employee employee1 = new Employee(
                employeeRequest.getSalary(),
                employeeRequest.getFname(),
                employeeRequest.getLname(),
                employeeRequest.getEmail(),
                employeeRequest.getAddress(),
                employeeRequest.getAddress2(),
                employeeRequest.getCity(),
                employeeRequest.getCounty(),
                employeeRequest.getPostalCode(),
                employeeRequest.getCountry(),
                employeeRequest.getPhoneNo(),
                employeeRequest.getBankNo(),
                employeeRequest.getNin()
        );

        Employee employee2 = new Employee(
                employeeRequest.getSalary(),
                employeeRequest.getFname(),
                employeeRequest.getLname(),
                employeeRequest.getEmail(),
                employeeRequest.getAddress(),
                employeeRequest.getAddress2(),
                employeeRequest.getCity(),
                employeeRequest.getCounty(),
                employeeRequest.getPostalCode(),
                employeeRequest.getCountry(),
                employeeRequest.getPhoneNo(),
                employeeRequest.getBankNo(),
                employeeRequest.getNin()
        );

        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @Test
    void insertEmployee_shouldReturnId_whenDaoReturnsId()
            throws DatabaseConnectionException, SQLException,
            BankNumberLengthException, SalaryTooLowException, NinLengthException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.insertEmployee(employeeRequest, conn)).thenReturn(expectedResult);

        int result = employeeService.insertEmployee(employeeRequest);

        assertEquals(expectedResult, result);
    }

    @Test
    void insertEmployee_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.insertEmployee(employeeRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> employeeService.insertEmployee(employeeRequest));
    }

    /*
    Mocking Exercise 1

    Write a unit test for the getEmployee method

    When the dao throws a SQLException

    Expect SQLException to be thrown

    This should pass without code changes
     */
    @Test
    public void getEmployee_shouldThrowSQLException_whenDaoThrowsSQLException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployee(1, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> employeeService.getEmployee(1));
    }

    /*
    Mocking Exercise 2

    Write a unit test for the getEmployee method

    When the dao throws a DatabaseConnectionException

    Expect DatabaseConnectionException to be thrown

    This should pass without code changes
     */
    @Test
    public void getEmployee_shouldThrowDatabaseException_whenDaoThrowsDatabaseException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        //Mockito.when(employeeService.getEmployee(1)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> employeeService.getEmployee(1));
    }

    /*
    Mocking Exercise 3

    Write a unit test for the getEmployee method

    When the dao returns an employee

    Expect the employee to be returned

    This should pass without code changes
     */
    @Test
    public void getEmployee_shouldReturnEmployee_whenDaoReturnsEmployee() throws DatabaseConnectionException, SQLException, DoesNotExistException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Employee employee = new Employee(
                employeeRequest.getSalary(),
                employeeRequest.getFname(),
                employeeRequest.getLname(),
                employeeRequest.getEmail(),
                employeeRequest.getAddress(),
                employeeRequest.getAddress2(),
                employeeRequest.getCity(),
                employeeRequest.getCounty(),
                employeeRequest.getPostalCode(),
                employeeRequest.getCountry(),
                employeeRequest.getPhoneNo(),
                employeeRequest.getBankNo(),
                employeeRequest.getNin()
        );
        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(employee);
        assertEquals(employee, employeeService.getEmployee(1));
    }

    /*
    Mocking Exercise 4

    Write a unit test for the getEmployee method

    When the dao returns null

    Expect DoesNotExistException to be thrown

    This should fail, make code changes to make this test pass
     */
    @Test
    public void getEmployee_shouldThrowDoesNotExist_whenDaoReturnsNull() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(null);
        assertThrows(DoesNotExistException.class, () -> employeeService.getEmployee(1));
    }

    /*
    Mocking Exercise 5

    Write a unit test for the getEmployees method

    When the dao returns a list of employees

    Expect the list of employees to be returned

    This should pass without code changes
     */
    @Test
    public void getEmployees_shouldReturnEmployeeList_whenDaoReturnsEmployeeList() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);


        Mockito.when(employeeDao.getEmployees(conn)).thenReturn(employeeList);

        assertEquals(employeeList, employeeService.getEmployees());
    }

    /*
    Mocking Exercise 6

    Write a unit test for the getEmployees method

    When the dao throws a SQL Exception

    Expect the SQL Exception to be returned

    This should pass without code changes
     */
    @Test
    public void getEmployees_throwsSQLException_whenDaoThrowsSQLException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployees(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> employeeService.getEmployees());
    }

    /*
    Mocking Exercise 7

    Write a unit test for the getEmployees method

    When the dao throws a DatabaseConnectionException

    Expect DatabaseConnectionException to be thrown

    This should pass without code changes
     */
    @Test
    public void getEmployees_throwsDBConnException_whenDaoThrowsDBConnException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class, () -> employeeService.getEmployees());
    }

    /*
    Mocking Exercise 8

    Write a unit test for the insertEmployee method

    When the dao throws a DatabaseConnectionException

    Expect DatabaseConnectionException to be thrown

    This should pass without code changes
     */
    @Test
    public void insertEmployee_throwsDatabaseConnectionException_whenDaoThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class, () -> employeeService.insertEmployee(employeeRequest));
    }

    /*
    Mocking Exercise 9

    Write a unit test for the insertEmployee method

    When the validator throws a SalaryTooLowException

    Expect SalaryTooLowException to be thrown

    This should pass without code changes
     */
    @Test
    public void insertEmployee_throwsSalaryTooLowException_whenValidatorThrowsSalaryTooLowException() throws BankNumberLengthException, SalaryTooLowException, NinLengthException {
        Mockito.when(employeeValidator.isValidEmployee(employeeRequest)).thenThrow(SalaryTooLowException.class);
        assertThrows(SalaryTooLowException.class, () -> employeeService.insertEmployee(employeeRequest));
    }

    /*
    Mocking Exercise 10

    Write a unit test for the insertEmployee method

    When the validator throws a BankNumberLengthException

    Expect BankNumberLengthException to be thrown

    This should pass without code changes
     */
    @Test
    public void insertEmployee_throwsBankNumLengthException_whenValidatorThrowsBankNumLengthException() throws BankNumberLengthException, SalaryTooLowException, NinLengthException {
        Mockito.when(employeeValidator.isValidEmployee(employeeRequest)).thenThrow(BankNumberLengthException.class);
        assertThrows(BankNumberLengthException.class, () -> employeeService.insertEmployee(employeeRequest));
    }
}