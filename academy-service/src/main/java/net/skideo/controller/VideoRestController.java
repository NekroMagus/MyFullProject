package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AcademyVideoDto;
import net.skideo.dto.VideoDto;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.video.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class VideoRestController {

    private final AcademyService academyService;
    private final VideoService videoService;

    @PostMapping("/video")
    public void addVideo(@Valid @RequestBody AcademyVideoDto videoDto) {
        academyService.addVideo(videoDto);
    }

    @GetMapping("/video")
    public Page<VideoDto> getMyVideos(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "50") int size) {
        return videoService.getMyVideos(page,size);
    }

}
