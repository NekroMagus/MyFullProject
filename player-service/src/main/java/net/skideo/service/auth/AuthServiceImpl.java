package net.skideo.service.auth;

import lombok.RequiredArgsConstructor;
import net.skideo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public boolean isUserExists(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean isPasswordMatch(String rowPassword, String encodedPassword) {
        return passwordEncoder.matches(rowPassword, encodedPassword);
    }
}
