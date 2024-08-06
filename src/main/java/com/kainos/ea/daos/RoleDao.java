package com.kainos.ea.daos;

import com.kainos.ea.enums.JobRoleColumn;
import com.kainos.ea.enums.Locations;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.RoleResponse;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    public List<RoleResponse> getAllJobRoles(
            final String orderBy, final String direction)
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = new ArrayList<>();

        // Validate orderBy and direction to prevent SQL injection
        if (orderBy != null && direction != null) {
            boolean isValidColumn = false;
            for (JobRoleColumn column : JobRoleColumn.values()) {
                if (column.getColumnName().equals(orderBy)) {
                    isValidColumn = true;
                    break;
                }
            }

            if (!isValidColumn
                    || (!"ASC".equalsIgnoreCase(direction)
                    && !"DESC".equalsIgnoreCase(direction))) {
                throw new IllegalArgumentException(
                        "Invalid order by or direction");
            }
        }

        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();

            String query = "SELECT jr.jobRoleId, jr.roleName, jr.location, "
                    + "b.bandValue AS band, "
                    + "c.capabilityName AS capability, "
                    + "jr.closingDate, jr.status "
                    + "FROM JobRoles jr "
                    + "JOIN Bands b ON jr.bandId = b.bandId "
                    + "JOIN Capabilities c ON jr.capabilityId = c.capabilityId "
                    + "WHERE jr.status = true ";

            // Apply ordering if orderBy and direction are provided
            if (orderBy != null && direction != null) {
                query += String.format("ORDER BY %s %s", orderBy, direction);
            }

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                RoleResponse roleResponse = new RoleResponse(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        Locations.fromString(resultSet.getString("location")),
                        resultSet.getString("band"),
                        resultSet.getString("capability"),
                        resultSet.getBoolean("status"),
                        resultSet.getTimestamp("closingDate")
                );
                roles.add(roleResponse);
            }
            return roles;
        } catch (DatabaseConnectionException e) {
            throw new FailedToRetrieveException(Entity.JOB_ROLE);
        }
    }
}
