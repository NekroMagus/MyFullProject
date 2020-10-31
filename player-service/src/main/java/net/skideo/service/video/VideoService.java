package net.skideo.service.video;

import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.User;
import net.skideo.model.Video;

import java.util.List;

public interface VideoService {

    void save(Video video);

    Video findById(long id);

    void updateLink(Video video);

    void updateLikes(Video video);

    void deleteById(long id);

    void estimateVideo(RatingDto ratingDto);

    int getRating(long idVideo);

    List<Video> getVideos(User user);

    void addVideo(String link);

    List<VideoDto> findVideos(User currentUser);

}
