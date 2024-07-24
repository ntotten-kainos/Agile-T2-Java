package com.kainos.ea;

import com.kainos.ea.controllers.RoleController;
import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.services.RoleService;
import com.kainos.ea.util.DatabaseConnector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;


public class WebServiceApplication extends
        Application<WebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "WebService";
    }

    @Override
    public void initialize(final Bootstrap<WebServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<WebServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final WebServiceConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final WebServiceConfiguration configuration,
                    final Environment environment) {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        environment.jersey()
                .register(new RoleController(
                                new RoleService(
                                        new RoleDao(),
                                        databaseConnector)));
    }
}
