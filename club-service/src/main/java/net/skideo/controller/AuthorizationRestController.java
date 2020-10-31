package net.skideo.controller;

import net.skideo.dto.AuthDto;
import net.skideo.dto.ClubRegDto;
import net.skideo.dto.TokenDto;
import net.skideo.exception.ClubAlreadyExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.Club;
import net.skideo.security.jwt.JwtProvider;
import net.skideo.service.auth.AuthorizationService;
import net.skideo.service.club.ClubService;
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
    private ClubService clubService;

    @Autowired
    private JwtProvider provider;

    @PostMapping("/authenticate")
    public TokenDto authenticate(@Valid @RequestBody AuthDto authDto) {

        if (!authorizationService.isCorrectPassword(authDto,clubService.findByLogin(authDto.getLogin()))) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }

        return new TokenDto(provider.generateToken(authDto.getLogin()));
    }


    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody ClubRegDto regDto) {
        if (authorizationService.isScoutExists(regDto.getLogin())) {
            throw new ClubAlreadyExistsException("User already exists");
        }
        clubService.save(new Club(regDto));
        return new TokenDto(provider.generateToken(regDto.getLogin()));
    }

}
