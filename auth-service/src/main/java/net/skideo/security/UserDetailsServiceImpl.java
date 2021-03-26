package net.skideo.security;

import net.skideo.model.Info;
import net.skideo.service.info.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private InfoService authService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Info auth = authService.findByLogin(login);
        return JwtInfo.authToJwtAuth(auth);
    }
}
