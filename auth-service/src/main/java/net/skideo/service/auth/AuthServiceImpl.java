package net.skideo.service.auth;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.exception.AuthNotFoundException;
import net.skideo.model.Auth;
import net.skideo.repository.AuthRepository;
import net.skideo.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder encoder;

    @Override
    public boolean isCorrectPassword(String rowPassword, String encodedPassword) {
        return encoder.matches(rowPassword,encodedPassword);
    }

    @Override
    public boolean isAuthExists(String login) {
        return authRepository.existsByLogin(login);
    }

    @Override
    public void addAuth(Auth auth) {
        auth.setPassword(encoder.encode(auth.getPassword()));
        authRepository.save(auth);
    }

    @Override
    public Auth findByLogin(String login) {
        return authRepository.findByLogin(login).orElseThrow(
                () -> new AuthNotFoundException("Auth not found")
        );
    }

    @Override
    public Auth getCurrentAuth(String token) {
        final String LOGIN_CURRENT_AUTH = jwtProvider.getLoginFromToken(token.substring(7));
        return findByLogin(LOGIN_CURRENT_AUTH);
    }

    @Override
    public void updateLoginAndPassword(String token,AuthDto authDto) {
        Auth dbAuth = getCurrentAuth(token);

        dbAuth.setLogin(authDto.getLogin());
        dbAuth.setPassword(encoder.encode(authDto.getPassword()));

        authRepository.save(dbAuth);
    }
}
