package net.skideo.controller;

import net.skideo.dto.TokenDto;
import net.skideo.dto.UserAuthDto;
import net.skideo.dto.UserRegistrationDto;
import net.skideo.dto.projections.UserProjection;
import net.skideo.exception.WrongLoginOrPasswordException;
import net.skideo.security.jwt.JwtTokenUtil;
import net.skideo.service.auth.AuthService;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.model.enums.RolePeople;
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
public class AuthRestController {

    private final UserService userService;
    private final AuthServiceFeignClient feignClient;


    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        ResponseEntity<TokenDto> response = feignClient.registration(new AuthDto(userRegistrationDto.getLogin(),
                userRegistrationDto.getPassword()));

        if (userRegistrationDto.getRolePeople() == RolePeople.AMATEUR
                && userRegistrationDto.isHasAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }
        Info info = new Info();
        info.setLogin(userRegistrationDto.getLogin());
        info.setPassword(userRegistrationDto.getPassword());

        userService.create(new User(info, userRegistrationDto.getRolePeople(), userRegistrationDto.isHasAgent()));

        return response;
    }

}
