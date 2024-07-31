package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.RoleResponse;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RoleIntegrationTest {
    private static final DropwizardAppExtension<WebServiceConfiguration> APP =
            new DropwizardAppExtension<>(WebServiceApplication.class);

    private String loginAndGetToken() {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/auth/login")
                .request()
                .post(Entity.json(new LoginRequest(System.getenv().get("VALID_TEST_EMAIL"), System.getenv().get("VALID_TEST_PASSWORD"))));

        return response.readEntity(String.class);
    }


    @Test
    public void getAllJobRoles_shouldReturnListOfJobRoles() {
        Client client = APP.client();

        String token = loginAndGetToken();

        Response response = client.target("http://localhost:8080/api/job-roles")
                .request()
                .header("Authorization", "Bearer " + token)
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        List<RoleResponse> roles = response.readEntity(List.class);
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
    }
}

