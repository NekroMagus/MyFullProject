package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.dto.TokenDto;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.Auth;
import net.skideo.security.JwtProvider;
import net.skideo.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {
        Auth auth = authService.findByLogin(authDto.getLogin());
        if(!authService.isCorrectPassword(authDto.getPassword(),auth.getPassword())) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }
        return new TokenDto(jwtProvider.generateToken(authDto.getLogin()));
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody AuthDto authDto) {
        if(authService.isAuthExists(authDto.getLogin())) {
            throw new AlreadyExistsException("Already exists");
        }

        authService.addAuth(new Auth(authDto.getLogin(),authDto.getPassword()));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TokenDto(jwtProvider.generateToken(authDto.getLogin())));
    }

    @GetMapping("/auth/me")
    public Auth getCurrentAuth(@RequestHeader("Authorization") String token) {
        final String LOGIN_CURRENT_AUTH = jwtProvider.getLoginFromToken(token);
        return authService.findByLogin(LOGIN_CURRENT_AUTH);
    }

}
