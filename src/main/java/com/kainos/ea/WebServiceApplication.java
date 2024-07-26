package com.kainos.ea;

import com.kainos.ea.controllers.AuthController;
import com.kainos.ea.controllers.RoleController;
import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.services.AuthService;
import com.kainos.ea.services.RoleService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;


public class WebServiceApplication extends
        Application<WebServiceConfiguration> {

    /**
     * Main Method.
     *
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        new WebServiceApplication().run(args);
    }

    /**
     * Get app name.
     *
     * @return the name of the App.
     */
    @Override
    public String getName() {
        return "WebService";
    }

    /**
     * Initialize the web app.
     *
     * @param bootstrap the application bootstrap
     */
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

    /**
     * Run the web app.
     *
     * @param configuration the parsed {@link WebServiceConfiguration} object
     * @param environment   the application's {@link Environment}
     */
    @Override
    public void run(final WebServiceConfiguration configuration,
                    final Environment environment) {

        environment.jersey()
                .register(new RoleController(
                        new RoleService(
                                new RoleDao()
                        )
                ));
        environment.jersey()
                .register(new AuthController(
                        new AuthService(
                                new AuthDao()
                        )
                ));
    }
}
