package net.skideo.service.info;

import net.skideo.dto.AuthDto;
import net.skideo.model.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;

public interface InfoService {

    boolean isPasswordCorrect(String rowPassword,String encodedPassword);

    boolean isAuthExists(String login);

    Info findByLogin(String login);

    void updateLoginAndPassword(AuthDto authDto);

    ResponseEntity<OAuth2AccessToken> generateToken(Map<String,String> parameters,String clientId) throws HttpRequestMethodNotSupportedException;

}
