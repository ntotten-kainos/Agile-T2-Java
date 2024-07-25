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

import static com.kainos.ea.util.PasswordEncoder.getPasswordEncoder;

public class AuthDao {
    /**
     * getUser checks the database for a user with matching credentials.
     * @param loginRequest contains the credentials to be checked.
     * @return a User object if authentication succeeds. Null otherwise.
     * @throws SQLException
     */
    public User getUser(final LoginRequest loginRequest)
            throws SQLException, DatabaseConnectionException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT `email`, `password`, `userRoleId`"
                            + "FROM `Users`"
                            + "WHERE `email` = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, loginRequest.getEmail());

            ResultSet resultSet = statement.executeQuery();
            Argon2PasswordEncoder arg2SpringSecurity = getPasswordEncoder();
            while (resultSet.next()) {
                String encodedPassword = resultSet.getString("password");
                if (
                        arg2SpringSecurity.matches(
                                loginRequest.getPassword(),
                                encodedPassword
                        )
                ) {
                    return new User(
                            // The user's email
                            resultSet.getString("email"),
                            // The Argon2 encoded user password
                            resultSet.getString("password"),
                            resultSet.getInt("userRoleId")
                    );
                }
            }
        }
        return null;
    }

    /*
     * When registering a user, be sure to create
     *  an Argon2PasswordEncoder instance to
     *  encode the raw password BEFORE storing it in the DB.
     * This will allow us to salt + hash the password
     *  securely and use argon2 to verify that
     *  the passwords match without exposing the plaintext.
     */
}
