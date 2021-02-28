package net.skideo.controller;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.RegDto;
import net.skideo.model.Scout;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.scout.ScoutService;
import lombok.RequiredArgsConstructor;
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

    private final AuthServiceFeignClient feignClient;
    private final ScoutService scoutService;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody RegDto regDto) {
        ResponseEntity<OAuth2AccessToken> response = feignClient.registration(regDto.getLogin(),regDto.getPassword(),clientId,
                                                                              clientSecret,"password", ServiceRole.SCOUT);

        scoutService.createScout(new Scout(regDto.getLogin(), regDto.getPassword(), regDto.getName(), regDto.getSurname()));

        return response;
    }
}
