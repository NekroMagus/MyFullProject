package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.ClubRegDto;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.model.Club;
import net.skideo.model.Info;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.club.ClubService;
import net.skideo.service.info.InfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final ClubService clubService;
    private final InfoService infoService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody ClubRegDto regDto) {
        if(infoService.isExiststByLogin(regDto.getLogin())) {
            throw new AlreadyExistsException("Club already exists");
        }

        Info info = new Info(regDto.getLogin(),regDto.getPassword(),regDto.getTitle());
        clubService.save(new Club(info,regDto.getLogoLink()));

        ResponseEntity<OAuth2AccessToken> response = feignClient.generateToken(regDto.getLogin(),regDto.getPassword(),clientId,
                                                                              clientSecret,"password");

        return response;
    }

}
