package net.skideo.service.auth;

import lombok.RequiredArgsConstructor;
import net.skideo.repository.ClubRepository;
import net.skideo.dto.AuthDto;
import net.skideo.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final ClubRepository clubRepository;
    private final PasswordEncoder encoder;

    @Override
    public boolean isScoutExists(String login) {
        return clubRepository.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(String rowPassword, String encodedPassword) {
        return encoder.matches(rowPassword,encodedPassword);
    }


}