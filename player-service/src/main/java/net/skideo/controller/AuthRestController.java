package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.TokenDto;
import net.skideo.dto.UserAuthDto;
import net.skideo.dto.UserRegistrationDto;
import net.skideo.exception.ResourceExistsException;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.model.User;
import net.skideo.model.enums.RolePeople;
import net.skideo.security.jwt.JwtTokenUtil;
import net.skideo.service.auth.AuthService;
import net.skideo.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final UserService userService;
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public TokenDto authenticate(@Valid @RequestBody UserAuthDto userAuthDto) {
        final User user = userService.findByLogin(userAuthDto.getLogin()).orElse(null);
        if (user == null || !authService.isPasswordMatch(userAuthDto.getPassword(), user.getPassword())) {
            throw new WrongLoginOrPasswordException("Wrong login or password");
        }
        return new TokenDto(jwtTokenUtil.generateToken(userAuthDto.getLogin()));
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        if (authService.isUserExists(userRegistrationDto.getLogin())) {
            throw new ResourceExistsException("User already exists");
        }
        if (userRegistrationDto.getRolePeople() == RolePeople.AMATEUR
                && userRegistrationDto.isHasAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }
        userService.create(userRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TokenDto(jwtTokenUtil.generateToken(userRegistrationDto.getLogin())));
    }

}
