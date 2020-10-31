package net.skideo.security;

import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import net.skideo.security.jwt.JwtClub;
import net.skideo.service.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClubService clubService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Club club = clubService.findByLogin(login);
        if(club==null) {
            throw new ClubNotFoundException("Club not found");
        }
        return JwtClub.scoutToJwtScout(club);
    }


}
