package com.kainos.ea.auth;

import com.kainos.ea.models.JwtToken;
import com.kainos.ea.models.UserRole;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Optional;

public class JwtAuthenticator implements Authenticator<String, JwtToken> {
    Key key;

    public JwtAuthenticator(final Key key) {
        this.key = key;
    }

    @Override
    public Optional<JwtToken> authenticate(final String token)
            throws AuthenticationException {
        try {
            Integer userRoleId = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("Role", Integer.class);

            JwtToken jwtToken = new JwtToken(new UserRole(userRoleId));

            return Optional.of(jwtToken);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
