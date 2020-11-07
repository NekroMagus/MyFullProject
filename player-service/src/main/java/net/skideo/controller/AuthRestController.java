package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.TokenDto;
import net.skideo.dto.UserAuthDto;
import net.skideo.dto.UserRegDto;
import net.skideo.exception.ResourceExistsException;
import net.skideo.exception.UserExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.User;
import net.skideo.security.jwt.JwtTokenUtil;
import net.skideo.service.auth.AuthService;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final UserService userService;
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public TokenDto authenticate(@Valid @RequestBody UserAuthDto userAuthDto) {
        final User USER = userService.findByLogin(userAuthDto.getLogin());
        if (!authService.isCorrectPassword(userAuthDto, USER)) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }
        return new TokenDto(jwtTokenUtil.generateToken(userAuthDto.getLogin()));
    }

    @PostMapping("/registration")
    public TokenDto registration(@Valid @RequestBody UserRegDto userRegDto) {
        if (authService.isUserExists(userRegDto.getLogin())) {
            throw new ResourceExistsException("User already exists");
        }
        userService.addUser(new User(userRegDto));
        return new TokenDto(jwtTokenUtil.generateToken(userRegDto.getLogin()));
    }


}
