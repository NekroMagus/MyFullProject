package net.skideo.service.video;

import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.User;
import net.skideo.model.Video;
import org.springframework.data.domain.Page;

public interface VideoService {

    Video findById(long id);

    void updateLink(Video video);

    void updateLikes(Video video);

    void deleteById(long id);

    void estimateVideo(RatingDto ratingDto, User user);

    int getRating(long idVideo);

    Page<VideoDto> findAllMyVideos(long idInfo, int page, int size);

    void addVideo(String token,String link);

    Page<VideoDto> findAllAnotherVideos(long idInfo, int page, int size);

}
