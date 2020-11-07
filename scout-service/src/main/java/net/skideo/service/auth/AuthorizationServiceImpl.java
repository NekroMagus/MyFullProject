package net.skideo.service.auth;

import net.skideo.repository.ScoutRepository;
import net.skideo.dto.AuthDto;
import net.skideo.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private ScoutRepository scoutRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isScoutExists(String login) {
        return scoutRepository.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(AuthDto authDto, Scout scout) {
        return scout!=null && encoder.matches(authDto.getPassword(),scout.getPassword());
    }
}
