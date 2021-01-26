package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.ClubRegDto;
import net.skideo.model.Club;
import net.skideo.service.club.ClubService;
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
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody ClubRegDto regDto) {
        ResponseEntity<OAuth2AccessToken> response = feignClient.registration(regDto.getLogin(),regDto.getPassword(),clientId,
                                                                              clientSecret,"password");

        clubService.save(new Club(regDto.getLogin(),regDto.getPassword(),regDto.getTitle(),regDto.getLogoLink()));

        return response;
    }

}
