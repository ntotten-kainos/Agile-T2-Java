package com.kainos.ea.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    private static final DropwizardAppExtension<WebServiceConfiguration> APP =
            new DropwizardAppExtension<>(WebServiceApplication.class);

    private static final LoginRequest VALID_LOGIN_REQUEST = new LoginRequest(
            "valid.admin@email.com",
            "admin!Pa$$word123"
    );

    private static final LoginRequest INVALID_LOGIN_REQUEST = new LoginRequest(
            "valid.admin@email.com",
            "admin!Pa$$"
    );

    @Test
    public void login_shouldReturnOK_whenValidLoginRequest() {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/auth/login")
                                .request()
                                .post(Entity.json(VALID_LOGIN_REQUEST));

        assertEquals(200, response.getStatus());
    }

    @Test
    public void login_shouldReturnBadRequest_whenInvalidLoginRequest() {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/auth/login")
                                .request()
                                .post(Entity.json(INVALID_LOGIN_REQUEST));

        assertEquals(400, response.getStatus());
    }

    @Test
    public void login_shouldReturnValidJwtToken_whenValidLoginRequest() throws JsonProcessingException {
        Client client = APP.client();

        Response response = client.target("http://localhost:8080/api/auth/login")
                .request()
                .post(Entity.json(VALID_LOGIN_REQUEST));

        // The JWT Token
        String responseBody = response.readEntity(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        String jwtTokenString = rootNode.get("jwtToken").asText();
        String loginResponseMessage = rootNode.get("message").asText();

        assertNotNull(responseBody);
        assertNotNull(jwtTokenString);
        assertEquals(loginResponseMessage, "Login Success.");
    }
}