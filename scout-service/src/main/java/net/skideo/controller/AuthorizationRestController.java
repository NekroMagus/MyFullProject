package net.skideo.controller;

import net.skideo.exception.ScoutsAlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.security.jwt.JwtProvider;
import net.skideo.service.auth.AuthorizationService;
import net.skideo.service.scout.ScoutService;
import net.skideo.dto.AuthDto;
import net.skideo.dto.RegDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthorizationRestController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ScoutService scoutService;

    @Autowired
    private JwtProvider provider;

    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {

        if (!authorizationService.isCorrectPassword(authDto,scoutService.findByLogin(authDto.getLogin()))) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }

        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }


    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody RegDto regDto) {
        if (authorizationService.isScoutExists(regDto.getLogin())) {
            throw new ScoutsAlreadyExistsException("User already exists");
        }
        scoutService.save(new Scout(regDto));
        return new TokenDto(provider.generateToken(regDto.getLogin()));
    }

}
