package net.skideo.service.auth;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.exception.AuthNotFoundException;
import net.skideo.model.Auth;
import net.skideo.repository.AuthRepository;
import net.skideo.security.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TokenEndpoint tokenEndpoint;

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
    public void updateLoginAndPassword(final String LOGIN_CURRENT_AUTH,AuthDto authDto) {
        Auth dbAuth = findByLogin(LOGIN_CURRENT_AUTH);

        dbAuth.setLogin(authDto.getLogin());
        dbAuth.setPassword(encoder.encode(authDto.getPassword()));

        authRepository.save(dbAuth);
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> generateToken(Map<String, String> parameters, String clientId) throws HttpRequestMethodNotSupportedException {
        JwtAuth jwtAuth = JwtAuth.authToJwtAuth(new Auth(clientId,null));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtAuth,null,null);

        return tokenEndpoint.postAccessToken(authentication,parameters);
    }
}
