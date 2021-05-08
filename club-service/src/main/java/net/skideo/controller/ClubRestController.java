package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import net.skideo.repository.ClubRepository;
import net.skideo.service.club.ClubService;
import net.skideo.service.scout.ScoutService;
import net.skideo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubRestController {

    private final ClubService clubService;
    private final ScoutService scoutService;
    private final AuthServiceFeignClient feignClient;
    private final ClubRepository clubRepository;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @GetMapping("/profile/{id}")
    public ClubProfileDto getProfile(@PathVariable(required = false) Long id) {
        if(id==null) {
            return clubService.getProfile(clubService.getIdByLogin(SecurityUtils.getLogin()));
        }
        return clubService.getProfile(id);
    }


    @GetMapping("/scouts")
    public Page<ScoutDto> getMyScouts(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "50") int size) {
        return scoutService.getMyScouts(page,size);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody ClubProfileDto clubProfileDto) {
        clubService.updateProfile(clubProfileDto);
    }

    @PutMapping("/auth")
    public ResponseEntity<OAuth2AccessToken> updateLoginAndPassword(@RequestBody AuthDto authDto) {
        clubService.updateLoginAndPassword(authDto);

        return feignClient.generateToken(authDto.getLogin(),authDto.getPassword(),clientId,clientSecret,"password");
    }

    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long idUser) {
        clubService.addUserToFavorite(idUser);
    }

    @GetMapping("/users/favorite")
    public Page<UserShortInfoClubDto> getFavoriteUsers(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return clubService.getFavoriteUsers(pageable);
    }

    // для тестов
    @GetMapping("/all")
    public List<Club> all() {
        return clubRepository.findAll();
    }

}



