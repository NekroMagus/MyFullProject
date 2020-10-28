package net.skideo.service.auth;

import net.skideo.dao.ScoutDao;
import net.skideo.dto.AuthDto;
import net.skideo.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private ScoutDao scoutDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isScoutExists(String login) {
        return scoutDao.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(AuthDto authDto, Scout scout) {
        return scout!=null && encoder.matches(authDto.getPassword(),scout.getPassword());
    }
}
