package com.kainos.ea.daos;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthDao {
    public User getUser(LoginRequest loginRequest) throws SQLException, DatabaseConnectionException {
        try(Connection connection = DatabaseConnector.getConnection()) {
            String query = "";
        }
        return null;
    }
}
