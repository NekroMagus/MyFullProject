package net.skideo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/api/authenticate","/academy/api/registration","/club/api/registration",
                             "/player/api/registration","/scout/api/registration","/academy/api/academy/**","/club/api/club/**","/notification/api/**","/player/api/all",
                             "/swagger-ui.html","/v2/api-docs","/swagger-resources", "/webjars/**",
                             "/academy/v2/api-docs","/club/v2/api-docs","/player/v2/api-docs","/scout/v2/api-docs","/notification/v2/api-docs","/oauth/v2/api-docs").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}