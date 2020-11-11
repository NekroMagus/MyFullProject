package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.projections.ScoutPasswordProjection;
import net.skideo.exception.ScoutsAlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.security.jwt.JwtProvider;
import net.skideo.service.auth.AuthorizationService;
import net.skideo.service.scout.ScoutService;
import net.skideo.dto.AuthDto;
import net.skideo.dto.RegDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Scout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorizationRestController {

    private final AuthorizationService authorizationService;
    private final ScoutService scoutService;
    private final JwtProvider provider;

    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {
        final ScoutPasswordProjection SCOUT = scoutService.getPasswordByLogin(authDto.getLogin());

        if (SCOUT != null && !authorizationService.isCorrectPassword(authDto.getPassword(), SCOUT.getPassword())) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }

        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }


    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody RegDto regDto) {
        if (authorizationService.isScoutExists(regDto.getLogin())) {
            throw new ScoutsAlreadyExistsException("Scout already exists");
        }
        scoutService.save(new Scout(regDto.getLogin(), regDto.getPassword(), regDto.getName(), regDto.getSurname()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDto(provider.generateToken(regDto.getLogin())));
    }
}
