package net.skideo.security;

import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.exception.AcademyNotFoundException;
import net.skideo.security.jwt.JwtAcademy;
import net.skideo.service.academy.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AcademyService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AcademyAuthProjection academy = service.findLoginAndPasswordByLogin(login);
        if(academy==null) {
            throw new AcademyNotFoundException("Academy not found");
        }
        return JwtAcademy.academyToJwtAcademy(academy);
    }
}
