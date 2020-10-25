package com.scout.service.security;

import com.scout.service.exception.ScoutNotFoundException;
import com.scout.service.security.jwt.JwtScout;
import com.scout.service.service.ScoutService;
import data.service.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ScoutService scoutService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Scout scout = scoutService.findByLogin(login);
        if(scout==null) {
            throw new ScoutNotFoundException("Scout not found");
        }
        return JwtScout.scoutToJwtScout(scout);
    }


}
