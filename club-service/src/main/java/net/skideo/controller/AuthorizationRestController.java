package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.dto.ClubRegDto;
import net.skideo.dto.TokenDto;
import net.skideo.dto.projections.ClubPasswordProjection;
import net.skideo.exception.ClubAlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.Club;
import net.skideo.security.jwt.JwtProvider;
import net.skideo.service.auth.AuthorizationService;
import net.skideo.service.club.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorizationRestController {

    private final AuthorizationService authorizationService;
    private final ClubService clubService;
    private final JwtProvider provider;

    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {
        final ClubPasswordProjection CLUB = clubService.getPasswordByLogin(authDto.getLogin());

        if (!authorizationService.isCorrectPassword(authDto.getPassword(), CLUB.getPassword())) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }
        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody ClubRegDto regDto) {
        if (authorizationService.isScoutExists(regDto.getLogin())) {
            throw new ClubAlreadyExistsException("User already exists");
        }

        clubService.save(new Club(regDto.getLogin(),regDto.getPassword(),regDto.getTitle()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TokenDto(provider.generateToken(regDto.getLogin())));
    }

}
