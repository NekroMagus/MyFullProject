package net.skideo.service.auth;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Auth;
import net.skideo.repository.AuthRepository;
import net.skideo.security.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Override
    public boolean isPasswordCorrect(String rowPassword, String encodedPassword) {
        return encoder.matches(rowPassword, encodedPassword);
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
    @Transactional(readOnly = true)
    public Auth findByLogin(String login) {
        return authRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException("Auth not found")
        );
    }

    @Override
    public void updateLoginAndPassword(AuthDto authDto) {
        Auth dbAuth = getCurrentAuth();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            dbAuth.setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            dbAuth.setPassword(encoder.encode(authDto.getPassword()));
        }

        authRepository.save(dbAuth);
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> generateToken(Map<String, String> parameters, String clientId) throws HttpRequestMethodNotSupportedException {
        JwtAuth jwtAuth = JwtAuth.authToJwtAuth(new Auth(clientId,null));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtAuth,null,null);

        return tokenEndpoint.postAccessToken(authentication,parameters);
    }

    @Transactional(readOnly = true)
    private Auth getCurrentAuth() {
        return findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}