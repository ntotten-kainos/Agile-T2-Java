package com.kainos.ea.authenticator;



import com.kainos.ea.models.JwtToken;
import com.kainos.ea.models.UserRole;
import io.dropwizard.auth.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.util.Optional;

import static org.junit.Assert.*;

public class JwtAuthenticatorTest {

    private JwtAuthenticator authenticator;
    private Key key;

    @Before
    public void setUp() {
        // Generate a secure key for testing
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        authenticator = new JwtAuthenticator(key);
    }

    @Test
    public void testAuthenticateSuccess() throws AuthenticationException {
        // Create a valid JWT token
        String token = Jwts.builder()
                .claim("Role", 1) // Assuming 1 corresponds to a valid user role (ADMIN)
                .signWith(key)
                .compact();

        Optional<JwtToken> result = authenticator.authenticate(token);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getUserRole().getUserRoleId());
        assertEquals(UserRole.ADMIN, result.get().getUserRole().getRoleName());
    }

    @Test
    public void testAuthenticateFailure_InvalidToken() throws AuthenticationException {
        String invalidToken = "invalidToken";

        Optional<JwtToken> result = authenticator.authenticate(invalidToken);

        assertFalse(result.isPresent());
    }

    @Test
    public void testAuthenticateFailure_TokenParsingException() throws AuthenticationException {
        // Create a token with an invalid signature
        Key wrongKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String invalidToken = Jwts.builder()
                .claim("Role", 1)
                .signWith(wrongKey)
                .compact();

        Optional<JwtToken> result = authenticator.authenticate(invalidToken);

        assertFalse(result.isPresent());
    }
}
