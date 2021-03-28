package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.*;
import net.skideo.service.academy.AcademyService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;

    private final Logger LOG = Logger.getLogger(AcademyRestController.class.getName());

    @GetMapping("/profile")
    public AcademyProfileDto getProfile(@RequestParam(required = false) Long id) {
        if(id==null) {
            LOG.log(Level.INFO,"Getting profile with id of current academy");
            return academyService.getProfile(academyService.getId(academyService.getLoginCurrentAcademy()));
        }
        LOG.log(Level.INFO,"Getting profile with id " + id);
        return academyService.getProfile(id);
    }

    @PutMapping("/auth")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@RequestBody AuthDto authDto) {
        LOG.log(Level.INFO,"Updating login and password...");
        academyService.updateLoginAndPassword(token,authDto);
        LOG.log(Level.INFO, "Updating login and password success");
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody AcademyProfileDto profileDto) {
        academyService.updateProfile(profileDto);
    }

}
