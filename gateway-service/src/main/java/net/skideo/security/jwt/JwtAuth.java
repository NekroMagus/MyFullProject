package net.skideo.security.jwt;

import net.skideo.model.Auth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtAuth implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static JwtAuth authToJwtAuth(Auth auth) {
        JwtAuth jwtAuth = new JwtAuth();

        jwtAuth.setLogin(auth.getLogin());
        jwtAuth.setPassword(auth.getPassword());

        if(auth.getRole()!=null) {
            jwtAuth.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(auth.getRole().name())));
        }

        return jwtAuth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void setLogin(String login) {
        this.login=login;
    }

    private void setPassword(String password) {
        this.password=password;
    }

    private void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities=authorities;
    }

}
