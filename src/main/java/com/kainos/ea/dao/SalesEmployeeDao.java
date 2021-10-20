package com.kainos.ea.dao;

import com.kainos.ea.model.SalesEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesEmployeeDao {
    public void insertSalesEmployee(SalesEmployee sEmp1, Connection c) throws SQLException {
        String insertSalesEmployeeQuery = "insert into salesEmployee (employeeId, commissionRate, totalSalesMonth)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt = c.prepareStatement(insertSalesEmployeeQuery);
        preparedStmt = c.prepareStatement(insertSalesEmployeeQuery);
        preparedStmt.setInt(1, sEmp1.getEmployeeId());
        preparedStmt.setFloat(2, sEmp1.getCommissionRate());
        preparedStmt.setInt(3, sEmp1.getSalesTotal());
        preparedStmt.executeUpdate();
    }

    public SalesEmployee getSalesEmployee(int employeeId, Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM salesEmployee "
                        + "WHERE employeeId = " + employeeId + ";");


        while (rs.next()) {
            return new SalesEmployee(
                    rs.getFloat("commissionRate"),
                    rs.getInt("totalSalesMonth"),
                    rs.getInt("employeeId"));
        }

        return null;
    }
}
