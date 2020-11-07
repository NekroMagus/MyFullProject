package net.skideo.service.auth;

import net.skideo.repository.ClubRepository;
import net.skideo.dto.AuthDto;
import net.skideo.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isScoutExists(String login) {
        return clubRepository.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(AuthDto authDto, Club club) {
        return club!=null && encoder.matches(authDto.getPassword(),club.getPassword());
    }
}