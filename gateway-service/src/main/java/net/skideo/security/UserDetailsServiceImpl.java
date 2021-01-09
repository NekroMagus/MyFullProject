package net.skideo.security;

import net.skideo.model.Auth;
import net.skideo.security.jwt.JwtAuth;
import net.skideo.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Auth auth = authService.findByLogin(login);
        return JwtAuth.authToJwtAuth(auth);
    }
}
