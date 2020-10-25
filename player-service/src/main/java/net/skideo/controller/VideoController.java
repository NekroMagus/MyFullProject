package net.skideo.controller;

import data.service.dto.VideoDto;
import data.service.model.User;
import data.service.model.Video;
import net.skideo.exception.VideoNotFoundException;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<?> getMyVideos() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Video> videos = user.getVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/videos")
    public List<VideoDto> getOtherVideos() {
        final User CURRENT_USER = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.findVideos(CURRENT_USER);
    }

}
