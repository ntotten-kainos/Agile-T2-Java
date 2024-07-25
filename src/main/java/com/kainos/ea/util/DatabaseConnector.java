package com.kainos.ea.util;

import com.kainos.ea.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnector {
    /**
     * The connection to the database.
     */
    private static Connection conn;
    private DatabaseConnector() { }

    /**
     * Attempts to establish a connection with a database
     * defined by DB_HOST, DB_USERNAME, DB_NAME
     * and DB_PASSWORD environment variables.
     * @return the database Connection object.
     * @throws SQLException
     * @throws DatabaseConnectionException
     */
    public static Connection getConnection()
            throws SQLException, DatabaseConnectionException {

        if (conn != null && !conn.isClosed()) {
            return conn;
        }

        try {
            String username = System.getenv().get("DB_USERNAME");
            String password = System.getenv().get("DB_PASSWORD");
            String host = System.getenv().get("DB_HOST");
            String name = System.getenv().get("DB_NAME");

            if (username == null || password == null || host == null
                    || name == null) {
                throw new IllegalArgumentException(
                        "Add the following properties to env vars: "
                                + "DB_USERNAME, DB_PASSWORD, DB_HOST"
                                + " and DB_NAME");
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + host + "/" + name, username, password);

            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionException(e);
        }
    }
}
