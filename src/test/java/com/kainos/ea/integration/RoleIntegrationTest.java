package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.models.Role;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RoleIntegrationTest {
    private static final DropwizardAppExtension<WebServiceConfiguration> APP =
            new DropwizardAppExtension<>(WebServiceApplication.class);

    @Test
    void getAllJobRoles_shouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<Role> response =
                client.target("http://localhost:8080/api/job-roles").request()
                        .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }
    @Test
    void getAllJobRoles_shouldReturnServerError_whenFailedToRetrieveException() {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/job-roles")
                .request()
                .get();
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.readEntity(String.class));
    }

}

