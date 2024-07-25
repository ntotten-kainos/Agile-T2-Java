package com.kainos.ea.services;

import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

import static com.kainos.ea.validators.LoginRequestValidator.validateLoginRequest;

public class AuthService {
    /**
     * Authentication Data Access Object.
     */
    private final AuthDao authDao;
    /**
     * Java Web Token Key.
     */
    private final Key jwtKey;

    /**
     * The lifespan of the JWT Token.
     * 8 hours in milliseconds.
     */
    private static final int TOKEN_LIFE = 28800000;

    /**
     * Constructor for Authentication Service.
     * @param authDaoParam
     */
    public AuthService(final AuthDao authDaoParam) {
        this.authDao = authDaoParam;
        this.jwtKey = Jwts.SIG.HS256.key().build();
    }

    /**
     * login method that attempts to authenticate user and
     * generate JWT.
     * @param loginRequest contains user credentials.
     * @return a valid JWT Token.
     * @throws SQLException
     * @throws DatabaseConnectionException
     * @throws LoginException
     */
    public String login(final LoginRequest loginRequest)
            throws SQLException, DatabaseConnectionException, LoginException {
        // Validate the loginRequest object data here before going any further.
        if (!validateLoginRequest(loginRequest)) {
            throw new LoginException(Entity.LOGIN_REQUEST);
        }
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new LoginException(Entity.LOGIN_REQUEST);
        }
        return generateJwtToken(user);
    }

    private String generateJwtToken(final User user) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_LIFE))
                .subject(user.getEmail())
                .claim("Role", user.getUserRoleId())
                .issuer("JobPortal_WebService")
                .signWith(jwtKey)
                .compact();
    }
}
