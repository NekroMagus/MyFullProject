package net.skideo.service.like;

import net.skideo.model.Like;

public interface LikeService {

    void save(Like like);

    Like findById(long id);

    void updateRating(Like like);

    void deleteById(long id);

}
