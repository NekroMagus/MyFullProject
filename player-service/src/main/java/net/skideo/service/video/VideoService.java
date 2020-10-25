package net.skideo.service.video;

import data.service.dto.RatingDto;
import data.service.model.Video;

public interface VideoService {

    void save(Video video);

    Video findById(long id);

    void updateLink(Video video);

    void updateLikes(Video video);

    void deleteById(long id);

    void estimateVideo(RatingDto ratingDto);

}
