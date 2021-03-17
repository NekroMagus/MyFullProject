package net.skideo.service.video;

import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Info;
import net.skideo.model.Video;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface VideoService {

    Page<VideoDto> getVideos(int page,int size);

    Page<VideoDto> getPopularVideos(int page,int size);

    Set<VideoDto> getRandomVideos(int size);

    Page<VideoDto> getMyVideos(int page,int size);

    void createVideo(String link);

    void estimateVideo(RatingDto dto);

    Video findById(long id);

    void save(Video video);

}
