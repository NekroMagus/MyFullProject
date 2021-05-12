package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.dto.base.SkideoDto;
import net.skideo.service.academy.AcademyService;
import net.skideo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    private final Logger LOG = Logger.getLogger(AcademyRestController.class.getName());

    @GetMapping("/profile")
    public SkideoDto<AcademyProfileDto> getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            LOG.log(Level.INFO,"Getting profile with id of current academy");
            return academyService.getProfile(academyService.getId(SecurityUtils.getLogin()));
        }
        LOG.log(Level.INFO,"Getting profile with id " + id);
        return academyService.getProfile(id);
    }

    @PutMapping("/auth")
    public SkideoDto<TokenDto> updateLoginAndPassword(@RequestBody AuthDto authDto) {
        LOG.log(Level.INFO,"Updating login and password...");
        academyService.updateLoginAndPassword(authDto);
        LOG.log(Level.INFO,"Updating login and password success");

        return new SkideoDto<TokenDto>(new TokenDto(feignClient.generateToken(authDto.getLogin(),authDto.getPassword(),clientId,clientSecret,"password").getBody()));
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody AcademyProfileDto profileDto) {
        LOG.log(Level.INFO,"Updating profile...");
        academyService.updateProfile(profileDto);
        LOG.log(Level.INFO,"Updating profile success");
    }

}
