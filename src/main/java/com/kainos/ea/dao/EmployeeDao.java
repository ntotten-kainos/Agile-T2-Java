package com.kainos.ea.dao;

import com.kainos.ea.model.Employee;
import com.kainos.ea.model.EmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public int insertEmployee(EmployeeRequest emp1, Connection c) throws SQLException {
        String insertEmployeeQuery = "insert into employee (fName, lName, email, address, address2," +
                "city, county, postalCode, country, phoneNo, salary, bankNo, nin)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = c.prepareStatement(insertEmployeeQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, emp1.getFname());
        preparedStmt.setString(2, emp1.getLname());
        preparedStmt.setString(3, emp1.getEmail());
        preparedStmt.setString(4, emp1.getAddress());
        preparedStmt.setString(5, emp1.getAddress2());
        preparedStmt.setString(6, emp1.getCity());
        preparedStmt.setString(7, emp1.getCounty());
        preparedStmt.setString(8, emp1.getPostalCode());
        preparedStmt.setString(9, emp1.getCountry());
        preparedStmt.setString(10, emp1.getPhoneNo());
        preparedStmt.setFloat(11, emp1.getSalary());
        preparedStmt.setString(12, emp1.getBankNo());
        preparedStmt.setString(13, emp1.getNin());

        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        int empNo = 0;

        try (ResultSet rs = preparedStmt.getGeneratedKeys()) {
            if (rs.next()) {
                empNo = rs.getInt(1);
            }
        }

        return empNo;
    }

    public Employee getEmployee(int employeeId, Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM employee "
                        + "WHERE employeeId = " + employeeId + ";");


        while (rs.next()) {
            Employee employee = new Employee(
                    rs.getFloat("salary"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("address2"),
                    rs.getString("city"),
                    rs.getString("county"),
                    rs.getString("postalCode"),
                    rs.getString("country"),
                    rs.getString("phoneNo"),
                    rs.getString("bankNo"),
                    rs.getString("nin")
            );

            employee.setEmployeeId(employeeId);
            return employee;
        }
        return null;
    }

    public List<Employee> getEmployees(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM employee;");

        List<Employee> employees = new ArrayList<>();

        while (rs.next()) {
            Employee employee = new Employee(
                    rs.getFloat("salary"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("address2"),
                    rs.getString("city"),
                    rs.getString("county"),
                    rs.getString("postalCode"),
                    rs.getString("country"),
                    rs.getString("phoneNo"),
                    rs.getString("bankNo"),
                    rs.getString("nin")
            );

            employee.setEmployeeId(rs.getInt("employeeId"));
            employees.add(employee);
        }
        return employees;
    }
}
