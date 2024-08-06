package com.kainos.ea;
import com.kainos.ea.authenticator.JwtAuthenticator;
import com.kainos.ea.authenticator.RoleAuthoriser;
import com.kainos.ea.controllers.AuthController;
import com.kainos.ea.controllers.RoleController;
import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.models.JwtToken;
import com.kainos.ea.services.AuthService;
import com.kainos.ea.services.RoleService;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import java.security.Key;


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
        Key jwtKey = Jwts.SIG.HS256.key().build();
        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<JwtToken>()
                        .setAuthenticator(new JwtAuthenticator(jwtKey))
                        .setAuthorizer(new RoleAuthoriser())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(
                JwtToken.class));
        environment.jersey()
                .register(new RoleController(
                        new RoleService(
                                new RoleDao()
                        )
                ));
        environment.jersey().register(new AuthController(
                new AuthService(new AuthDao(),
                jwtKey)));
    }
}