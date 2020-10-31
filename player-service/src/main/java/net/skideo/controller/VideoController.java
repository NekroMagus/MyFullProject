package net.skideo.controller;

import net.skideo.dto.GetRatingDto;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @PostMapping("/profile/video")
    public ResponseEntity<?> addVideo(@RequestBody String link) {
        videoService.addVideo(link);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    @GetMapping("/profile/video")
    public ResponseEntity<?> getMyVideos() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Video> videos = videoService.getVideos(user);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/videos")
    public List<VideoDto> getOtherVideos() {
        final User CURRENT_USER = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return videoService.findVideos(CURRENT_USER);
    }

    @PostMapping("/rating")
    public void estimateVideo(@Valid @RequestBody RatingDto ratingDto) {
        videoService.estimateVideo(ratingDto);
    }

    @GetMapping("/rating/{id}")
    public GetRatingDto getRating(@PathVariable("id") long id) {
        return new GetRatingDto(videoService.getRating(id));
    }

}
