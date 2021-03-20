package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.*;
import net.skideo.service.academy.AcademyService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;

    @GetMapping("/profile")
    public AcademyProfileDto getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            return academyService.getProfile(academyService.getId(academyService.getLoginCurrentAcademy()));
        }
        return academyService.getProfile(id);
    }

    @PutMapping("/auth")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@RequestBody AuthDto authDto) {
        academyService.updateLoginAndPassword(token,authDto);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody AcademyProfileDto profileDto) {
        academyService.updateProfile(profileDto);
    }

}
