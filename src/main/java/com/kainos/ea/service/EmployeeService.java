package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.model.Employee;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.EmployeeRequest;

import java.sql.SQLException;
import java.util.List;

import static com.kainos.ea.util.DatabaseConnector.getConnection;

public class EmployeeService {
    public EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int insertEmployee(EmployeeRequest employee) throws DatabaseConnectionException, SQLException {
        return employeeDao.insertEmployee(employee, getConnection());
    }

    public Employee getEmployee(int employeeId) throws DatabaseConnectionException, SQLException {
        return employeeDao.getEmployee(employeeId, getConnection());
    }

    public List<Employee> getEmployees() throws DatabaseConnectionException, SQLException {
        return employeeDao.getEmployees(getConnection());
    }
}
