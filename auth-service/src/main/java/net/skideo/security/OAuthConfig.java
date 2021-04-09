package net.skideo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("getAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final int TOKEN_VALIDITY = 3600;
    private final String[] GRANT_TYPES = {"password","refresh_token"};
    private final String[] SCOPES = {"read","write"};

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("skideo");
        converter.setVerifierKey("skideo");
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(userDetailsService);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()

                .withClient("club-service")
                .secret(passwordEncoder.encode("club-service"))
                .refreshTokenValiditySeconds(TOKEN_VALIDITY)
                .accessTokenValiditySeconds(TOKEN_VALIDITY)
                .authorizedGrantTypes(GRANT_TYPES)
                .scopes(SCOPES)

                .and()

                .withClient("academy-service")
                .secret(passwordEncoder.encode("academy-service"))
                .refreshTokenValiditySeconds(TOKEN_VALIDITY)
                .accessTokenValiditySeconds(TOKEN_VALIDITY)
                .authorizedGrantTypes(GRANT_TYPES)
                .scopes(SCOPES)

                .and()

                .withClient("player-service")
                .secret(passwordEncoder.encode("player-service"))
                .refreshTokenValiditySeconds(TOKEN_VALIDITY)
                .accessTokenValiditySeconds(TOKEN_VALIDITY)
                .authorizedGrantTypes(GRANT_TYPES)
                .scopes(SCOPES)

                .and()

                .withClient("scout-service")
                .secret(passwordEncoder.encode("scout-service"))
                .refreshTokenValiditySeconds(TOKEN_VALIDITY)
                .accessTokenValiditySeconds(TOKEN_VALIDITY)
                .authorizedGrantTypes(GRANT_TYPES)
                .scopes(SCOPES);
    }

}
