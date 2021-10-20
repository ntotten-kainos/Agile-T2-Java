package com.kainos.ea.service;

import com.kainos.ea.dao.SalesEmployeeDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.SalesEmployee;

import java.sql.SQLException;

import static com.kainos.ea.util.DatabaseConnector.getConnection;

public class SalesEmployeeService {
    public SalesEmployeeDao salesEmployeeDao;

    public SalesEmployeeService() {
        this.salesEmployeeDao = new SalesEmployeeDao();
    }

    public void insertSalesEmployee(SalesEmployee sEmp1) throws DatabaseConnectionException, SQLException {
        salesEmployeeDao.insertSalesEmployee(sEmp1, getConnection());
    }

    public SalesEmployee getSalesEmployee(int employeeId) throws DatabaseConnectionException, SQLException {
        return salesEmployeeDao.getSalesEmployee(employeeId, getConnection());
    }
}
