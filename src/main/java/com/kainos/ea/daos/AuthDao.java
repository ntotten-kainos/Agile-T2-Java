package com.kainos.ea.daos;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import com.kainos.ea.util.DatabaseConnector;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

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

            Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
            String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());


            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, loginRequest.getEmail());
            statement.setString(2, encodedPassword);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        // The user's email
                        resultSet.getString("email"),
                        // The Argon2 encoded user password
                        resultSet.getString("password")
                );
            }
        }
        return null;
    }

    /**
     * When registering a user, be sure to create an Argon2PasswordEncoder instance to
     *  encode the raw password BEFORE storing it in the DB.
     * This will allow us to salt + hash the password securely and use argon2 to verify that
     *  the passwords match without exposing the plaintext.
     */
}
