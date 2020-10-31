package net.skideo.service.auth;

import net.skideo.dao.ClubDao;
import net.skideo.dto.AuthDto;
import net.skideo.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private ClubDao clubDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isScoutExists(String login) {
        return clubDao.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(AuthDto authDto, Club club) {
        return club!=null && encoder.matches(authDto.getPassword(),club.getPassword());
    }
}