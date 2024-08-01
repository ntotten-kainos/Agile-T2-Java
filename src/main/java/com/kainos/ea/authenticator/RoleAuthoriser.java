package com.kainos.ea.authenticator;

import com.kainos.ea.models.JwtToken;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;

import javax.ws.rs.container.ContainerRequestContext;

public class RoleAuthoriser implements Authorizer<JwtToken> {
    @Override
    public boolean authorize(final JwtToken jwtToken, final String s) {
        return jwtToken.getUserRole().getRoleName().equals(s);
    }

    @Override
    public boolean authorize(final JwtToken principal, final String role,
                             final @Nullable
                             ContainerRequestContext requestContext) {
        return principal.getUserRole().getRoleName().equals(role);
    }
}
