package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.AuthDto;
import net.skideo.dto.RegAcademyDto;
import net.skideo.model.Academy;
import net.skideo.model.Info;
import net.skideo.service.academy.AcademyService;
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

    private final AcademyService academyService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody RegAcademyDto regAcademyDto) {
        ResponseEntity<OAuth2AccessToken> response = feignClient.registration(regAcademyDto.getLogin(),regAcademyDto.getPassword(),clientId,
                                                                              clientSecret,"password");

        Info info = new Info(regAcademyDto.getLogin(),regAcademyDto.getPassword(),regAcademyDto.getCity(),
                             regAcademyDto.getCountry(),regAcademyDto.getTitle());

        academyService.save(new Academy(info,regAcademyDto.getNumberPlayers()));

        return response;
    }




}
