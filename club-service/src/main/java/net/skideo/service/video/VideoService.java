package net.skideo.service.video;

import net.skideo.dto.VideoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VideoService {

    Page<VideoDto> findVideos(Pageable pageable);
}
