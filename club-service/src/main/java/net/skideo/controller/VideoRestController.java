package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.VideoDto;
import net.skideo.service.club.ClubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/videos")
public class VideoRestController {

    private final ClubService clubService;

    @GetMapping
    public List<VideoDto> findVideos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "50") int size) {
        return clubService.findVideos(page, size);
    }

}
