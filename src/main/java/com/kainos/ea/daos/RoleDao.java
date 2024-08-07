package com.kainos.ea.daos;

import com.kainos.ea.enums.Direction;
import com.kainos.ea.enums.JobRoleColumn;
import com.kainos.ea.enums.Locations;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.JobRoleResponse;
import com.kainos.ea.models.RoleResponse;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            boolean isValidColumn = isValidEnumValue(
                    JobRoleColumn.class, orderBy);
            boolean isValidDirection = isValidEnumValue(
                    Direction.class, direction);

            if (!isValidColumn || !isValidDirection) {
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
        } catch (SQLException e) {
            System.err.println("SQLException occurred: " + e.getMessage());
            throw e;
        }
    }
    public JobRoleResponse getRoleById(final int id)
            throws SQLException, FailedToRetrieveException {
        String query =
                "SELECT jr.jobRoleId, jr.roleName, jr.description, "
                        + "jr.responsibilities, jr.location, jr.specification, "
                        + "b.bandValue AS band, c.capabilityName AS capability,"
                        + " jr.closingDate, jr.status FROM JobRoles jr "
                        + "JOIN Bands b ON jr.bandId = b.bandId "
                        + "JOIN Capabilities c ON jr.capabilityId ="
                        + " c.capabilityId WHERE jr.jobRoleId = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new JobRoleResponse(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("description"),
                        resultSet.getString("responsibilities"),
                        Locations.fromString(resultSet.getString("location")),
                        resultSet.getString("band"),
                        resultSet.getString("capability"),
                        resultSet.getBoolean("status"),
                        resultSet.getTimestamp("closingDate"),
                        resultSet.getString("specification"));
            }
            return null;
        } catch (DatabaseConnectionException e) {
            throw new FailedToRetrieveException(Entity.JOB_ROLE);
        }
    }

    private static <T extends Enum<T>> boolean isValidEnumValue(
            final Class<T> enumClass, final String value) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
