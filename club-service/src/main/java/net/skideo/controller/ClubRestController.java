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
    public ClubProfileDto getProfile() {
        return clubService.getProfile();
    }

    @PostMapping("/scout/{id}")
    public void addScout(@PathVariable("id") long id) {
        clubService.addScout(id);
    }

    @DeleteMapping("/scout/{id}")
    public void removeScout(@PathVariable("id") long id) {
        clubService.removeScout(id);
    }

    @GetMapping("/scouts")
    public Page<ScoutDto> getScouts(@RequestParam int page, @RequestParam int size) {
        return clubService.getScouts(page,size);
    }

    @PutMapping("/scout/{id}")
    public void setRegionScout(@PathVariable("id") long id, @RequestParam String region) {
        clubService.setRegionScout(id, region);
    }

    @GetMapping("/scout")
    public Page<ScoutDto> getScoutByRegion(@RequestParam int page,@RequestParam int size,@RequestParam String region) {
        return clubService.getScoutsByRegion(region,page,size);
    }


    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long idUser) {
        clubService.addUserToFavorite(idUser);
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



