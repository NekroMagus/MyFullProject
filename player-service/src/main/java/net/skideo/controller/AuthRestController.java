package net.skideo.controller;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.UserRegistrationDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Info;
import net.skideo.model.Player;
import net.skideo.service.info.InfoService;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RolePeople;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
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
    private final InfoService infoService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        if(infoService.isExistsByLogin(userRegistrationDto.getLogin())) {
            throw new NotFoundException("User not found");
        }

        if (userRegistrationDto.getRolePeople() == RolePeople.AMATEUR
                && userRegistrationDto.isHasAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }

        Info info = new Info(userRegistrationDto.getLogin(),userRegistrationDto.getPassword());

        userService.create(new Player(info, userRegistrationDto.getRolePeople(), userRegistrationDto.isHasAgent()));

        ResponseEntity<OAuth2AccessToken> response = feignClient.generateToken(userRegistrationDto.getLogin(), userRegistrationDto.getPassword(), clientId,
                clientSecret, "password");

        return response;
    }

}
