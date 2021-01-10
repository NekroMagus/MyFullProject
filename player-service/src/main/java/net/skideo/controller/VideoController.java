package net.skideo.controller;

import net.skideo.dto.GetRatingDto;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.dto.VideoLinkDto;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.service.like.LikeService;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import lombok.RequiredArgsConstructor;
import net.skideo.exception.UserRatedException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VideoController {

    private final UserService userService;
    private final VideoService videoService;
    private final LikeService likeService;

    @GetMapping("/profile/video")
    public Page<VideoDto> getMyVideos(@RequestHeader("Authorization") String token,
                                      @RequestParam(defaultValue = "0", required = false) int page,
                                      @RequestParam(defaultValue = "50", required = false) int size) {
        User CURRENT_USER = userService.getCurrentUser(token);
        return videoService.findAllMyVideos(CURRENT_USER.getId(), page, size);
    }

    @GetMapping("/videos")
    public Page<VideoDto> getOtherVideos(@RequestHeader("Authorization") String token,
                                         @RequestParam(defaultValue = "0", required = false) int page,
                                         @RequestParam(defaultValue = "50", required = false) int size) {
        User CURRENT_USER = userService.getCurrentUser(token);
        return videoService.findAllAnotherVideos(CURRENT_USER.getId(), page, size);
    }

    @PostMapping("/profile/video")
    public ResponseEntity<?> addVideo(@RequestHeader("Authorization") String token,@Valid @RequestBody VideoLinkDto dto) {
        videoService.addVideo(token,dto.getLink());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/rating")
    public void estimateVideo(@RequestHeader("Authorization") String token,@Valid @RequestBody RatingDto ratingDto) {
        User user = userService.getCurrentUser(token);
        Optional<Like> like = likeService.findByUserIdAndVideoId(ratingDto.getIdVideo(), user.getId());
        if (like.isPresent()) {
            throw new UserRatedException("You already liked this video");
        }
        videoService.estimateVideo(ratingDto, user);
    }

    @GetMapping("/rating/{id}")
    public GetRatingDto getRating(@PathVariable("id") long id) {
        return new GetRatingDto(videoService.getRating(id));
    }

}
