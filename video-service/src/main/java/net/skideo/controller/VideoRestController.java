package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.CommentADto;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.dto.VideoLinkDto;
import net.skideo.model.Comment;
import net.skideo.model.Video;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.CommentRepository;
import net.skideo.repository.VideoRepository;
import net.skideo.service.comment.CommentService;
import net.skideo.service.video.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/video")
public class VideoRestController {

    private final VideoService videoService;
    private final VideoRepository repository;

    Logger log = Logger.getLogger(VideoRestController.class.getName());

    @PostMapping
    public void addVideo(@Valid @RequestBody VideoLinkDto videoLinkDto) {
        videoService.createVideo(videoLinkDto.getLink());
    }

    @GetMapping("/my")
    public Page<VideoDto> getMyVideos(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "50") int size) {
        return videoService.getMyVideos(page,size);
    }

    @GetMapping("/other")
    public Page<VideoDto> getOtherVideos(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "50") int size) {
        return videoService.getVideos(page,size);
    }

    @GetMapping("/other/popular")
    public Page<VideoDto> getPopularVideos(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "50") int size) {
        return videoService.getPopularVideos(page,size);
    }

    @GetMapping("/other/random")
    public Set<VideoDto> getRandomVideos(@RequestParam(defaultValue = "50") int size) {
        return videoService.getRandomVideos(size);
    }

    @PostMapping("/estimate")
    public void estimateVideo(@Valid @RequestBody RatingDto ratingDto) {
        videoService.estimateVideo(ratingDto);
    }

    @GetMapping("/all")
    public List<Video> all() {
        return repository.findAllByInfoServiceRole(ServiceRole.USER);
    }

}
