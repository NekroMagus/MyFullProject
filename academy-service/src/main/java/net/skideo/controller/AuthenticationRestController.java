package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.dto.RegAcademyDto;
import net.skideo.dto.TokenDto;
import net.skideo.exception.AcademyAlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.Academy;
import net.skideo.model.Auth;
import net.skideo.security.jwt.JwtProvider;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationService authService;
    private final AcademyService academyService;
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
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody RegAcademyDto regAcademyDto) {
        if(authService.isAuthExists(regAcademyDto.getLogin())) {
            throw new AcademyAlreadyExistsException("Academy already exists");
        }
        academyService.save(new Academy(regAcademyDto.getLogin(),regAcademyDto.getPassword(),regAcademyDto.getCity(),
                                        regAcademyDto.getCity(),regAcademyDto.getCountry(),regAcademyDto.getNumberPlayers()));
        authService.addAuth(new Auth(regAcademyDto.getLogin(),regAcademyDto.getPassword()));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TokenDto(jwtProvider.generateToken(regAcademyDto.getLogin())));
    }

}
