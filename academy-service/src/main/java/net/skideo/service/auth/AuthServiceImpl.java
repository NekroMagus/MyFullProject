package net.skideo.service.auth;

import lombok.RequiredArgsConstructor;
import net.skideo.service.academy.AcademyService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AcademyService academyService;
    private final PasswordEncoder encoder;

    @Override
    public boolean isCorrectPassword(String rowPassword, String encodedPassword) {
        return encoder.matches(rowPassword,encodedPassword);
    }

    @Override
    public boolean isAcademyExists(String login) {
        return academyService.isExistsByLogin(login);
    }
}
