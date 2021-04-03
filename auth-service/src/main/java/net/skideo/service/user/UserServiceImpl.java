package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.User;
import net.skideo.repository.UserRepository;
import net.skideo.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isPasswordCorrect(String rowPassword, String encodedPassword) {
        return encoder.matches(rowPassword, encodedPassword);
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(
                () -> new NotFoundException("Auth not found")
        );
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> generateToken(Map<String, String> parameters, String clientId) throws HttpRequestMethodNotSupportedException {
        JwtUser jwtUser = JwtUser.userToJwtUser(new User(clientId));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser,null,null);

        return tokenEndpoint.postAccessToken(authentication,parameters);
    }

}