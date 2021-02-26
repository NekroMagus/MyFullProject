package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import net.skideo.repository.ClubRepository;
import net.skideo.service.club.ClubService;
import net.skideo.service.scout.ScoutService;
import org.springframework.data.domain.Page;
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
    public ClubProfileDto getProfile() {
        return clubService.getProfile();
    }


    @GetMapping("/scouts")
    public Page<ScoutDto> getScouts(@RequestParam int page, @RequestParam int size) {
        return scoutService.getScouts(page,size);
    }

    @PutMapping
    public void updateProfile(@Valid )

    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long idUser) {
        clubService.addUserToFavorite(idUser);
    }

    // для тестов
    @GetMapping("/all")
    public List<Club> all() {
        return clubRepository.findAll();
    }

}



