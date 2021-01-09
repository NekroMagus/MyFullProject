package net.skideo.controller;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.AuthDto;
import net.skideo.dto.TokenDto;
import net.skideo.dto.UserRegistrationDto;
import net.skideo.model.Info;
import net.skideo.model.User;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RolePeople;
import org.springframework.http.ResponseEntity;
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
