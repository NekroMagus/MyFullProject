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

    public static JwtAuth academyToJwtAcademy(Auth auth) {
        JwtAuth jwtAcademy = new JwtAuth();
        jwtAcademy.setLogin(auth.getLogin());
        jwtAcademy.setPassword(auth.getPassword());
        jwtAcademy.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(auth.getRole().name())));
        return jwtAcademy;
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

    private void setPassword(String password) {
        this.password=password;
    }

    private void setLogin(String login) {
        this.login=login;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
