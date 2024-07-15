package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.*;
import com.kainos.ea.model.Employee;
import com.kainos.ea.model.EmployeeRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.EmployeeValidator;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final EmployeeValidator employeeValidator;
    private final DatabaseConnector databaseConnector;

    public EmployeeService(EmployeeDao employeeDao, EmployeeValidator employeeValidator, DatabaseConnector databaseConnector) {
        this.employeeDao = employeeDao;
        this.employeeValidator = employeeValidator;
        this.databaseConnector = databaseConnector;
    }

    public int insertEmployee(EmployeeRequest employee)
            throws DatabaseConnectionException, SQLException,
            BankNumberLengthException, SalaryTooLowException, NinLengthException {
        employeeValidator.isValidEmployee(employee);

        return employeeDao.insertEmployee(employee, databaseConnector.getConnection());
    }

    public Employee getEmployee(int employeeId) throws DatabaseConnectionException, SQLException, DoesNotExistException {
        Employee employeeToReturn = employeeDao.getEmployee(employeeId, databaseConnector.getConnection());
        if (employeeToReturn == null) {
            throw new DoesNotExistException();
        }
        return employeeToReturn;
    }

    public List<Employee> getEmployees() throws DatabaseConnectionException, SQLException {
        return employeeDao.getEmployees(databaseConnector.getConnection());
    }
}
