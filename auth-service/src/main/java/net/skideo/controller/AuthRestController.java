package net.skideo.controller;

import net.skideo.dto.AuthDto;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.Info;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.info.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    @Autowired
    private InfoService infoService;

    @PostMapping("/authenticate")
    public ResponseEntity<OAuth2AccessToken> authenticate(@RequestParam String login, @RequestParam String password, @RequestParam String clientId,
                                                          @RequestParam String clientSecret, @RequestParam String grantType, @RequestParam ServiceRole serviceRole) throws IllegalAccessException, HttpRequestMethodNotSupportedException {
        Info auth = infoService.findByLogin(login);

        if (auth.getServiceRole() != serviceRole) {
            throw new IllegalAccessException();
        }

        if (!infoService.isPasswordCorrect(password, auth.getPassword())) {
            throw new WrongLoginOrPasswordException();
        }

        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", login);
        parameters.put("password", password);
        parameters.put("client_secret", clientSecret);
        parameters.put("grant_type", grantType);
        parameters.put("scope", "read write");

        return infoService.generateToken(parameters, clientId);
    }

    @PostMapping("/token")
    public ResponseEntity<OAuth2AccessToken> generateToken(@RequestParam String login, @RequestParam String password, @RequestParam String clientId,
                                                   @RequestParam String clientSecret, @RequestParam String grantType) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", login);
        parameters.put("password", password);
        parameters.put("client_secret", clientSecret);
        parameters.put("grant_type", grantType);
        parameters.put("scope", "read write");

        return infoService.generateToken(parameters, clientId);
    }

    @PutMapping("/auth/data")
    public void updateLoginAndPassword(@RequestBody AuthDto authDto) {
        infoService.updateLoginAndPassword(authDto);
    }

}
