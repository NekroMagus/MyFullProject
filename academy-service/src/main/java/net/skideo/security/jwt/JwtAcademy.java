package net.skideo.security.jwt;

import net.skideo.model.Academy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAcademy implements UserDetails {

    private String login;
    private String password;

    public static JwtAcademy academyToJwtAcademy(Academy academy) {
        JwtAcademy jwtAcademy = new JwtAcademy();
        jwtAcademy.setLogin(academy.getLogin());
        jwtAcademy.setPassword(academy.getPassword());
        return jwtAcademy;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
