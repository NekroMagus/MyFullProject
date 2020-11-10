package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.GetRatingDto;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.dto.VideoLinkDto;
import net.skideo.exception.UserRatedException;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.service.like.LikeService;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
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
    public Page<VideoDto> getMyVideos(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "50", required = false) int size
    ) {
        User user = userService.getCurrentUser();
        return videoService.findAllMyVideos(user.getId(), page, size);
    }

    @GetMapping("/videos")
    public Page<VideoDto> getOtherVideos(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "50", required = false) int size
    ) {
        final User USER = userService.getCurrentUser();
        return videoService.findAllAnotherVideos(USER.getId(), page, size);
    }

    @PostMapping("/profile/video")
    public ResponseEntity<?> addVideo(@Valid @RequestBody VideoLinkDto dto) {
        videoService.addVideo(dto.getLink());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/rating")
    public void estimateVideo(@Valid @RequestBody RatingDto ratingDto) {
        User user = userService.getCurrentUser();
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
