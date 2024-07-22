package com.kainos.ea.daos;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    public User getUser(LoginRequest loginRequest) throws SQLException, DatabaseConnectionException {
        try(Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT `email`, `password`"
                            + "FROM `Users`"
                            + "WHERE `email` = ?"
                            + "AND `password` = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, loginRequest.getEmail());
            statement.setString(2, loginRequest.getPassword());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        }
        return null;
    }
}
