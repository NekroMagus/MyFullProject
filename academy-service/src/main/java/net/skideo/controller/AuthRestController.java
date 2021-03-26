package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.RegAcademyDto;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.model.Academy;
import net.skideo.model.Info;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.academy.AcademyService;
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

    private final AcademyService academyService;
    private final InfoService infoService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody RegAcademyDto regAcademyDto) {

        if(infoService.isExistsByLogin(regAcademyDto.getLogin())) {
            throw new AlreadyExistsException("Academy already exists");
        }

        Info info = new Info(regAcademyDto.getLogin(),regAcademyDto.getPassword(),regAcademyDto.getCity(),
                regAcademyDto.getCountry(),regAcademyDto.getTitle());

        academyService.createAcademy(new Academy(info,regAcademyDto.getNumberPlayers()));

        ResponseEntity<OAuth2AccessToken> response = feignClient.generateToken(regAcademyDto.getLogin(),regAcademyDto.getPassword(),clientId,
                                                                              clientSecret,"password");

        return response;
    }




}
