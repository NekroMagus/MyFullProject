package net.skideo.service;

import net.skideo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;

public interface UserService {

    boolean isPasswordCorrect(String rowPassword,String encodedPassword);

    User findByLogin(String login);

    ResponseEntity<OAuth2AccessToken> generateToken(Map<String,String> parameters,String clientId) throws HttpRequestMethodNotSupportedException;

}
