package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.models.LoginRequest;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    private static final DropwizardAppExtension<WebServiceConfiguration> APP =
            new DropwizardAppExtension<>(WebServiceApplication.class);

    private static final LoginRequest VALID_LOGIN_REQUEST = new LoginRequest(
            "valid@email.com",
            "V4lid!Pa$$word123"
    );

    @Test
    public void login_shouldReturnValidJwtToken() {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/auth/login")
                                .request()
                                .post(Entity.json(VALID_LOGIN_REQUEST));

        assertEquals("", response.getStatus());
    }
}
