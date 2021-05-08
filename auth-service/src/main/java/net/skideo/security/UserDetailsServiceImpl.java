package net.skideo.security;

import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${spring.admin.login}")
    private String adminLogin;

    @Value("${spring.admin.password}")
    private String adminPassword;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if(login.equals(adminLogin)) {
            User user = new User();
            user.setLogin(adminLogin);
            user.setPassword(encoder.encode(adminPassword));
            user.setServiceRole(ServiceRole.ADMIN);
            return JwtUser.userToJwtUser(user);
        }

        User user = userService.findByLogin(login);
        return JwtUser.userToJwtUser(user);
    }
}
