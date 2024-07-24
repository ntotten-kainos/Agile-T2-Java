package com.kainos.ea;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class WebServiceConfiguration extends Configuration {
    /**
     * Swagger Bundle Config.
     */
    @Valid
    @NotNull
    private final SwaggerBundleConfiguration swagger =
            new SwaggerBundleConfiguration();

    /**
     * Get Swagger.
     * @return a Swagger Config.
     */
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger() {
        swagger.setResourcePackage("com.kainos.ea.controller");
        String[] schemes = {"http", "https"};
        swagger.setSchemes(schemes);
        return swagger;
    }
}
