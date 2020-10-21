package net.skideo.controller;

import data.service.model.User;
import net.skideo.exception.VideoNotFoundException;
import net.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {



    @Autowired
    private UserService userService;

    @PostMapping("/profile/video")
    public ResponseEntity<?> addVideo(@RequestBody String link) {
        userService.addVideo(link);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    @GetMapping("/profile/video")
    public ResponseEntity<?> getVideo() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        String link = user.getVideo();
        if (link == null) {
            throw new VideoNotFoundException();
        }
        return new ResponseEntity<>(link, HttpStatus.OK);
    }




}
