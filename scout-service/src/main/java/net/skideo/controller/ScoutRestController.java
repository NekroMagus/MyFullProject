package net.skideo.controller;


import net.skideo.dto.*;
import net.skideo.model.Player;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.scout.ScoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scout")
@RequiredArgsConstructor
public class ScoutRestController {

    private final ScoutService scoutService;
    private final PlayerRepository playerRepository;

    // для тестов
    /* ------------------------------------------------- */

    @GetMapping("/all")
    public List<Player> all() {
        return playerRepository.findAll();
    }

    /* ------------------------------------------------- */

    @GetMapping("/profile")
    public ProfileDto getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            return scoutService.getProfile(scoutService.getIdByLogin(scoutService.getLoginCurrentScout()));
        }
        return scoutService.getProfile(id);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody UpdateProfileDto dto) {
        scoutService.updateProfile(dto);
    }

    @PutMapping("/auth")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@RequestBody AuthDto authDto) {
        scoutService.updateLoginAndPassword(token,authDto);
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



