package com.kainos.ea.daos;

import com.kainos.ea.enums.Locations;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.Role;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    public List<Role> getAllJobRoles()
            throws SQLException, FailedToRetrieveException {
        List<Role> roles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT jobRoleId, roleName, location,"
                            + "bandId, capabilityId, "
                            + "closingDate, status "
                            + "FROM JobRoles where status = true;");

            while (resultSet.next()) {
                Role role = new Role(resultSet.getInt("jobRoleId"),
                                        resultSet.getString("roleName"),
                                        Locations.fromString(
                                                resultSet.getString(
                                                "location")),
                                        resultSet.getInt("bandId"),
                                        resultSet.getInt("capabilityId"),
                        resultSet.getBoolean("status"),
                        resultSet.getTimestamp("closingDate")
                                );
                roles.add(role);
            }
            return roles;
        } catch (DatabaseConnectionException e) {
            throw new FailedToRetrieveException(Entity.JOB_ROLE);
        }
    }
}
