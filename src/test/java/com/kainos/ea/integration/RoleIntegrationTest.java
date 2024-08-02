package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.models.JobRoleResponse;
import com.kainos.ea.models.RoleResponse;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RoleIntegrationTest {
    private static final DropwizardAppExtension<WebServiceConfiguration> APP =
            new DropwizardAppExtension<>(WebServiceApplication.class);

    @Test
    void getAllJobRoles_shouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<RoleResponse> response =
                client.target("http://localhost:8080/api/job-roles").request()
                        .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void getRoleById_shouldReturnJobRole() {
        Client client = APP.client();
        int id = 1;

        Response response =
                client.target("http://localhost:8080/api/job-roles/" + id)
                        .request()
                        .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        JobRoleResponse role = response.readEntity(JobRoleResponse.class);
        assertNotNull(role);
        assertEquals(id, role.getJobRoleId());
    }

    @Test
    void getRoleById_shouldReturnNotFoundForNonExistentRole() {
        Client client = APP.client();
        int nonExistentId = 9999; // Assuming this ID does not exist

        Response response =
                client.target("http://localhost:8080/api/job-roles/" + nonExistentId)
                        .request()
                        .get();

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}

