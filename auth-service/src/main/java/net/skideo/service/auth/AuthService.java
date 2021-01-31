package net.skideo.service.auth;

import net.skideo.dto.AuthDto;
import net.skideo.model.Auth;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;

public interface AuthService {

    boolean isAuthExists(String login);

    void addAuth(Auth auth);

    Auth findByLogin(String login);

    void updateLoginAndPassword(AuthDto authDto);

    ResponseEntity<OAuth2AccessToken> generateToken(Map<String,String> parameters,String clientId) throws HttpRequestMethodNotSupportedException;

}
