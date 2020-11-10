package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Club;
import net.skideo.service.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.ColorUIResource;
import java.util.List;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubRestController {

    private final ClubService clubService;

    @GetMapping("/profile")
    public ResponseEntity<ClubProfileDto> getProfile() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clubService.getProfile());
    }

    @PostMapping("/scout/{id}")
    public void addScout(@PathVariable("id") long id) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.addScout(id,CURRENT_CLUB);
    }

    @DeleteMapping("/scout/{id}")
    public void removeScout(@PathVariable("id") long id) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.removeScout(id,CURRENT_CLUB);
    }

    @GetMapping("/scouts")
    public ResponseEntity<List<ScoutDto>> getScouts() {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clubService.getScouts(CURRENT_CLUB));
    }

    @PutMapping("/scout/{id}")
    public void setRegionScout(@PathVariable("id") long id,@RequestParam String region) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.setRegionScout(id,region,CURRENT_CLUB);
    }

    @GetMapping("/scout")
    public ResponseEntity<List<ScoutDto>> getScoutByRegion(@RequestParam String region) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clubService.getScoutsByRegion(region, CURRENT_CLUB));
    }

    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long idUser) {
        final Club CURRENT_CLUB = clubService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        clubService.addUserToFavorite(idUser,CURRENT_CLUB);
    }

    @GetMapping("/videos")
    public ResponseEntity<List<VideoDto>> findVideos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clubService.findVideos(page,size));
    }

}

