package net.skideo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtTokenStore tokenStore;

    private final String[] AUTH_URL = {"/api/authenticate","/api/registration"};
    private final String[] SWAGGER_URL = {"/swagger-ui.html","/v2/api-docs","/swagger-resources", "/webjars/**"};

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTH_URL).permitAll()
                .antMatchers(SWAGGER_URL).permitAll()
                .anyRequest().authenticated();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
    }

}
