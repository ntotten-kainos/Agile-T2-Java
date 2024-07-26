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
    /**
     * Gets.
     * @return Returns list of open job roles
     * @throws SQLException
     * @throws FailedToRetrieveException
     */
    public List<RoleResponse> getAllJobRoles()
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT jr.jobRoleId, jr.roleName, jr.location,"
                            + "b.bandValue AS band,"
                            + "c.capabilityName AS capability, "
                            + "jr.closingDate, jr.status "
                            + "FROM JobRoles jr "
                            + "JOIN Bands b ON jr.bandId = b.bandId "
                            + "JOIN Capabilities c ON jr.capabilityId = "
                            + "c.capabilityId "
                            + "WHERE jr.status = true");


            while (resultSet.next()) {
                RoleResponse roleResponse = new RoleResponse(resultSet.getInt("jobRoleId"),
                                        resultSet.getString("roleName"),
                                        Locations.fromString(
                                                resultSet.getString(
                                                "location")),
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
