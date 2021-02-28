package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.VideoDto;
import net.skideo.service.video.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/videos")
public class VideoRestController {

    private final VideoService videoService;

    @GetMapping
    public Page<VideoDto> findVideos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return videoService.findVideos(pageable);
    }

}
