package net.skideo.security.jwt;

import net.skideo.model.Club;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtClub implements UserDetails {

    private String login;
    private String password;

    public static JwtClub scoutToJwtScout(Club club) {
        JwtClub jwtScout = new JwtClub();
        jwtScout.setLogin(club.getLogin());
        jwtScout.setPassword(club.getPassword());
        return jwtScout;
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

    private void setLogin(String login) {
        this.login = login;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
