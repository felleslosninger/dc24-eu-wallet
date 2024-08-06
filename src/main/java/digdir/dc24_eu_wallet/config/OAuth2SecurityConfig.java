package digdir.dc24_eu_wallet.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

/**
 * Configures OAuth2 security for the application. It defines how OAuth2 authentication
 * and authorization are handled throughout the application, including login, logout, and
 * access control based on OAuth2 client registrations.
 */
@Configuration
@EnableWebFluxSecurity
public class OAuth2SecurityConfig {

    /**
     * For managing client registrations.
     */
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    // Constructor injection
    @Autowired
    public OAuth2SecurityConfig(ReactiveClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    /**
     * Configures the security filter chain, which defines how HTTP requests are handled based on
     * their paths and authentication requirements.
     *
     * @param http the ServerHttpSecurity object to configure security.
     * @return the configured SecurityWebFilterChain.
     */
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges ->
                        exchanges
                                .pathMatchers("/", "/error", "/logout/callback", "/test/**", "/callback/**", "/Presentation/**", "/styles/**", "/js/**").permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .authorizationRequestResolver(authorizationRequestResolver(this.clientRegistrationRepository)))
                .oidcLogout(logout -> logout
                        .backChannel(Customizer.withDefaults()))
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler()));
        return http.build();
    }

    /**
     * Configures the OAuth2 authorization request resolver, which resolves
     * authorization requests based on client registrations.
     *
     * @param clientRegistrationRepository the repository of client registrations.
     * @return the configured authorization request resolver.
     */
    private ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
            ReactiveClientRegistrationRepository clientRegistrationRepository) {
        DefaultServerOAuth2AuthorizationRequestResolver authorizationRequestResolver = new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
        authorizationRequestResolver.setAuthorizationRequestCustomizer(authorizationRequestCustomizer());
        return  authorizationRequestResolver;
    }

    /**
     * Customizes the OAuth2 authorization request with additional parameters.
     *
     * @return the consumer to customize the authorization request.
     */
    private Consumer<OAuth2AuthorizationRequest.Builder> authorizationRequestCustomizer() {
        return customizer -> customizer
                .additionalParameters(params ->
                        params.put( "authorization_details",
                                "[{\"type\":\"ansattporten:altinn:service\",\"resource\": \"urn:altinn:resource:2480:40\", \"allow_multiple_organizations\": \"true\"}]"));
    }

    /**
     * Configures the OIDC logout success handler, which manages redirection
     * after successful logout during OIDC-based operations.
     *
     * @return the configured OIDC logout success handler.
     */
    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository);

        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logout/callback");

        return oidcLogoutSuccessHandler;
    }
}