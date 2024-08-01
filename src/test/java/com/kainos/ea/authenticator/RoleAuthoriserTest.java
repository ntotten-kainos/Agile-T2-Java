package com.kainos.ea.authenticator;

import com.kainos.ea.authenticator.RoleAuthoriser;
import com.kainos.ea.models.JwtToken;
import com.kainos.ea.models.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.container.ContainerRequestContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoleAuthoriserTest {

    private RoleAuthoriser roleAuthoriser;
    private JwtToken jwtToken;
    private UserRole userRole;

    @Before
    public void setUp() {
        roleAuthoriser = new RoleAuthoriser();
        userRole = Mockito.mock(UserRole.class);
        jwtToken = new JwtToken(userRole);
    }

    @Test
    public void testAuthorize_WithMatchingRole() {
        Mockito.when(userRole.getRoleName()).thenReturn(UserRole.ADMIN_USER);
        assertTrue(roleAuthoriser.authorize(jwtToken, UserRole.ADMIN_USER));
    }

    @Test
    public void testAuthorize_WithNonMatchingRole() {
        Mockito.when(userRole.getRoleName()).thenReturn(UserRole.APPLICANT_USER);
        assertFalse(roleAuthoriser.authorize(jwtToken, UserRole.ADMIN_USER));
    }

    @Test
    public void testAuthorize_WithContainerRequestContext_WithMatchingRole() {
        Mockito.when(userRole.getRoleName()).thenReturn(UserRole.ADMIN_USER);
        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);
        assertTrue(roleAuthoriser.authorize(jwtToken, UserRole.ADMIN_USER, requestContext));
    }

    @Test
    public void testAuthorize_WithContainerRequestContext_WithNonMatchingRole() {
        Mockito.when(userRole.getRoleName()).thenReturn(UserRole.APPLICANT_USER);
        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);
        assertFalse(roleAuthoriser.authorize(jwtToken, UserRole.ADMIN_USER, requestContext));
    }
}

