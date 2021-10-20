package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.model.Employee;
import com.kainos.ea.exception.DatabaseConnectionException;

import java.sql.SQLException;

import static com.kainos.ea.util.DatabaseConnector.getConnection;

public class EmployeeService {
    public EmployeeDao employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDao();
    }

    public void insertEmployee(Employee employee) throws DatabaseConnectionException, SQLException {
        employeeDao.insertEmployee(employee, getConnection());
    }

    public Employee getEmployee(int employeeId) throws DatabaseConnectionException, SQLException {
        return employeeDao.getEmployee(employeeId, getConnection());
    }
}
