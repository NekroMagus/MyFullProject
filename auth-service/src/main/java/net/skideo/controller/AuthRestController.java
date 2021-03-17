package net.skideo.controller;

import net.skideo.dto.AuthDto;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.Auth;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.AuthRepository;
import net.skideo.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<OAuth2AccessToken> authenticate(@RequestParam String login,@RequestParam String password, @RequestParam String clientId,
                                                          @RequestParam String clientSecret, @RequestParam String grantType, @RequestParam ServiceRole serviceRole) throws IllegalAccessException, HttpRequestMethodNotSupportedException {
        Auth auth = authService.findByLogin(login);

        if(auth.getServiceRole()!=serviceRole) {
            throw new IllegalAccessException();
        }

        if(!authService.isPasswordCorrect(password,auth.getPassword())) {
            throw new WrongLoginOrPasswordException();
        }

        Map<String,String> parameters = new HashMap<>();
        parameters.put("username",login);
        parameters.put("password",password);
        parameters.put("client_secret",clientSecret);
        parameters.put("grant_type",grantType);
        parameters.put("scope","read write");

        return authService.generateToken(parameters,clientId);
    }

    @RequestMapping(method = RequestMethod.POST,path = "/registration",consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OAuth2AccessToken> registration(@RequestParam String login,@RequestParam String password,@RequestParam ServiceRole serviceRole, @RequestParam String clientId,
                                                          @RequestParam String clientSecret, @RequestParam String grantType) throws HttpRequestMethodNotSupportedException {
        if(authService.isAuthExists(login)) {
            throw new AlreadyExistsException("Already exists");
        }

        authService.addAuth(new Auth(login,password,serviceRole));

        Map<String,String> parameters = new HashMap<>();
        parameters.put("username",login);
        parameters.put("password",password);
        parameters.put("client_secret",clientSecret);
        parameters.put("grant_type",grantType);
        parameters.put("scope","read write");

        return authService.generateToken(parameters,clientId);
    }

    @PutMapping("/auth/data")
    public void updateLoginAndPassword(@RequestBody AuthDto authDto) {
        authService.updateLoginAndPassword(authDto);
    }

}
