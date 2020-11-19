package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Club;
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

    @GetMapping("/profile")
    public ClubProfileDto getProfile() {
        return clubService.getProfile();
    }

    @PostMapping("/scout/{id}")
    public void addScout(@PathVariable("id") long id) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.addScout(id, CURRENT_CLUB);
    }

    @DeleteMapping("/scout/{id}")
    public void removeScout(@PathVariable("id") long id) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.removeScout(id, CURRENT_CLUB);
    }

    @GetMapping("/scouts")
    public Page<ScoutDto> getScouts(@RequestParam int page, @RequestParam int size) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return clubService.getScouts(CURRENT_CLUB,page,size);
    }


    @PutMapping("/scout/{id}")
    public void setRegionScout(@PathVariable("id") long id, @RequestParam String region) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.setRegionScout(id, region, CURRENT_CLUB);
    }

    @GetMapping("/scout")
    public Page<ScoutDto> getScoutByRegion(@RequestParam int page,@RequestParam int size,@RequestParam String region) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return clubService.getScoutsByRegion(region, CURRENT_CLUB.getId(),page,size);
    }


    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long idUser) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.addUserToFavorite(idUser, CURRENT_CLUB);
    }

    @GetMapping("/videos")
    public List<VideoDto> findVideos(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "50") int size) {
        return clubService.findVideos(page, size);
    }

}



