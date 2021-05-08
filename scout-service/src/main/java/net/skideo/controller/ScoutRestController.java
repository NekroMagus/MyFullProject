package net.skideo.controller;


import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.model.Player;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.scout.ScoutService;
import lombok.RequiredArgsConstructor;
import net.skideo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scout")
@RequiredArgsConstructor
public class ScoutRestController {

    private final ScoutService scoutService;
    private final AuthServiceFeignClient feignClient;
    private final PlayerRepository playerRepository;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    // для тестов
    /* ------------------------------------------------- */

    @GetMapping("/all")
    public List<Player> all() {
        return playerRepository.findAll();
    }

    /* ------------------------------------------------- */

    @GetMapping("/profile/{id}")
    public ProfileDto getProfile(@PathVariable(required = false) Long id) {
        if(id==null) {
            return scoutService.getProfile(scoutService.getIdByLogin(SecurityUtils.getLogin()));
        }
        return scoutService.getProfile(id);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody UpdateProfileDto dto) {
        scoutService.updateProfile(dto);
    }

    @PutMapping("/auth")
    public ResponseEntity<OAuth2AccessToken> updateLoginAndPassword(@RequestBody AuthDto authDto) {
        scoutService.updateLoginAndPassword(authDto);

        return feignClient.generateToken(authDto.getLogin(),authDto.getPassword(),clientId,clientSecret,"password");
    }

    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long id) {
        scoutService.addUserToFavorite(id);
    }

    @GetMapping("/user/favorite")
    public Page<UserShortInfoClubDto> getFavoriteUsers(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return scoutService.getFavoriteUsers(pageable);
    }

}



