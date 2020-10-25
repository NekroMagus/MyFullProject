package net.skideo.service.like;

import data.service.model.Like;

public interface LikeService {

    void save(Like like);

    Like findById(long id);

    void updateRating(Like like);

    void deleteById(long id);

}
