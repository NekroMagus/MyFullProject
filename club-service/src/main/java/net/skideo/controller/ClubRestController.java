package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import net.skideo.repository.ClubRepository;
import net.skideo.service.club.ClubService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubRestController {

    private final ClubService clubService;
    private final ClubRepository clubRepository;

    @GetMapping("/profile")
    public ClubProfileDto getProfile(@RequestHeader("Authorization") String token) {
        return clubService.getProfile(token);
    }

    @PostMapping("/scout/{id}")
    public void addScout(@RequestHeader("Authorization") String token,@PathVariable("id") long id) {
        Club CURRENT_CLUB = clubService.getCurrentClub(token);
        clubService.addScout(id, CURRENT_CLUB);
    }

    @DeleteMapping("/scout/{id}")
    public void removeScout(@RequestHeader("Authorization") String token,@PathVariable("id") long id) {
        Club CURRENT_CLUB = clubService.getCurrentClub(token);
        clubService.removeScout(id, CURRENT_CLUB);
    }

    @GetMapping("/scouts")
    public Page<ScoutDto> getScouts(@RequestHeader("Authorization") String token,@RequestParam int page, @RequestParam int size) {
        Club CURRENT_CLUB = clubService.getCurrentClub(token);
        return clubService.getScouts(CURRENT_CLUB,page,size);
    }


    @PutMapping("/scout/{id}")
    public void setRegionScout(@RequestHeader("Authorization") String token,@PathVariable("id") long id, @RequestParam String region) {
        Club CURRENT_CLUB = clubService.getCurrentClub(token);
        clubService.setRegionScout(id, region, CURRENT_CLUB);
    }

    @GetMapping("/scout")
    public Page<ScoutDto> getScoutByRegion(@RequestHeader("Authorization") String token,@RequestParam int page,@RequestParam int size,@RequestParam String region) {
        Club CURRENT_CLUB = clubService.getCurrentClub(token);
        return clubService.getScoutsByRegion(region, CURRENT_CLUB.getId(),page,size);
    }


    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@RequestHeader("Authorization") String token,@PathVariable("id") long idUser) {
        Club CURRENT_CLUB = clubService.getCurrentClub(token);
        clubService.addUserToFavorite(idUser, CURRENT_CLUB);
    }

    @GetMapping("/videos")
    public List<VideoDto> findVideos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "50") int size) {
        return clubService.findVideos(page, size);
    }

    // для тестов
    @GetMapping("/all")
    public List<Club> all() {
        return clubRepository.findAll();
    }

}



