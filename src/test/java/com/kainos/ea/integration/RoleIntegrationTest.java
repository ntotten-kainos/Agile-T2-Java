package com.kainos.ea.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.models.JobRoleResponse;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.RoleResponse;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
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

        Response response =
                client.target("http://localhost:8080/api/auth/login")
                        .request()
                        .post(Entity.json(new LoginRequest(
                                System.getenv().get("VALID_TEST_EMAIL"),
                                System.getenv().get("VALID_TEST_PASSWORD")
                        )));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        String token = response.readEntity(String.class);
        assertNotNull(token);
        return token;
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

    @Test
    public void getAllJobRoles_shouldReturn401WhenNoTokenProvided() {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/job-roles")
                .request()
                .get();

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(),
                response.getStatus());
    }

    @Test
    void getRoleById_shouldReturnJobRole() throws JsonProcessingException {
        Client client = APP.client();
        String token = loginAndGetToken();
        int id = 1;

        Response response =
                client.target("http://localhost:8080/api/job-roles/" + id)
                        .request()
                        .header("Authorization", "Bearer " + token)
                        .get();

        // Log response body for debugging
        String responseBody = response.readEntity(String.class);
        System.out.println("Response body: " + responseBody);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // Deserialize the response body
        JobRoleResponse role = APP.getObjectMapper().readValue(responseBody, JobRoleResponse.class);
        assertNotNull(role);
        assertEquals(id, role.getJobRoleId());
    }

    @Test
    void getRoleById_shouldReturnNotFoundForNonExistentRole() {
        Client client = APP.client();
        String token = loginAndGetToken();
        int nonExistentId = 9999; // Assuming this ID does not exist

        Response response =
                client.target(
                                "http://localhost:8080/api/job-roles/" + nonExistentId)
                        .request()
                        .header("Authorization", "Bearer " + token)
                        .get();

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                response.getStatus());
    }
}
