package net.skideo.service.video;

import net.skideo.dto.AcademyVideoDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Video;
import org.springframework.data.domain.Page;

public interface VideoService {

    void addVideo(AcademyVideoDto videoDto);

    Page<VideoDto> getMyVideos(int page, int size);

}
