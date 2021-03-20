package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.*;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import net.skideo.repository.ClubRepository;
import net.skideo.service.club.ClubService;
import net.skideo.service.scout.ScoutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubRestController {

    private final ClubService clubService;
    private final ScoutService scoutService;
    private final ClubRepository clubRepository;

    @GetMapping("/profile")
    public ClubProfileDto getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            return clubService.getProfile(clubService.getId(clubService.getLoginCurrentClub()));
        }
        return clubService.getProfile(id);
    }


    @GetMapping("/scouts")
    public Page<ScoutDto> getScouts(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "50") int size) {
        return scoutService.getScouts(page,size);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody ClubProfileDto clubProfileDto) {
        clubService.updateProfile(clubProfileDto);
    }

    @PutMapping("/auth")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@RequestBody AuthDto authDto) {
        clubService.updateLoginAndPassword(token,authDto);
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



