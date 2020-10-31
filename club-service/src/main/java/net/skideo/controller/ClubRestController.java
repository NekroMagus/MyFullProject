package net.skideo.controller;

import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.service.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubRestController {

    @Autowired
    private ClubService clubService;

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
    public List<ScoutDto> getScouts() {
        return clubService.getScouts();
    }

    @PutMapping("/scout/{id}")
    public void setRegionScout(@PathVariable("id") long id,@RequestParam String region) {
        clubService.setRegionScout(id,region);
    }

    @GetMapping("/scout")
    public List<ScoutDto> getScoutByRegion(@RequestParam String region) {
        return clubService.getScoutsByRegion(region);
    }

    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long idUser) {
        clubService.addUserToFavorite(idUser);
    }

    @GetMapping("/videos")
    public List<VideoDto> findVideos() {
        return clubService.findVideos();
    }

}

