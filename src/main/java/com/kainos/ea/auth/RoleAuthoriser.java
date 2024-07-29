package com.kainos.ea.auth;

import com.kainos.ea.models.JwtToken;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;

import javax.ws.rs.container.ContainerRequestContext;

public class RoleAuthoriser implements Authorizer<JwtToken> {
    @Override
    public boolean authorize(JwtToken jwtToken, String s) {
        return jwtToken.getUserRole().getUserRoleName().equals(s);
    }

    @Override
    public boolean authorize(JwtToken principal, String role, @Nullable ContainerRequestContext requestContext) {
        return principal.getUserRole().getUserRoleName().equals(role);
    }
}
