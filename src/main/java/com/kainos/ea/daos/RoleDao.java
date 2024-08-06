package com.kainos.ea.daos;

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

    public List<RoleResponse> getAllJobRoles()
            throws SQLException, FailedToRetrieveException {
        return getOrderedJobRoles(null, null);
    }

    @SuppressWarnings("checkstyle:FinalParameters")
    public List<RoleResponse> getOrderedJobRoles(
            String orderBy, String direction)
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = new ArrayList<>();

        // Set default order if orderBy and direction are null or "default"
        if (orderBy == null
                || "default".equalsIgnoreCase(orderBy)
                || direction == null || "default".equalsIgnoreCase(direction)) {
            orderBy = "roleName"; // Default column to sort by
            direction = "ASC"; // Default direction
        }

        // Validate orderBy and direction to prevent SQL injection
        List<String> validColumns = List.of(
                "roleName", "location", "band",
                "capability", "closingDate", "status");
        if (!validColumns.contains(orderBy) || (
                !"ASC".equalsIgnoreCase(direction)
                        && !"DESC".equalsIgnoreCase(direction))) {
            throw new IllegalArgumentException("Invalid order by or direction");
        }

        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();

            String query = String.format(
                    "SELECT jr.jobRoleId, jr.roleName, jr.location, "
                            + "b.bandValue AS band, "
                            + "c.capabilityName AS capability, "
                            + "jr.closingDate, jr.status "
                            + "FROM JobRoles jr "
                            + "JOIN Bands b ON jr.bandId = b.bandId "
                            + "JOIN Capabilities c "
                            + "ON jr.capabilityId = c.capabilityId "
                            + "WHERE jr.status = true "
                            + "ORDER BY %s %s", orderBy, direction);

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

