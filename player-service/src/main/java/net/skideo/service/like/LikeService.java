package net.skideo.service.like;

import net.skideo.model.Like;

import java.util.Optional;

public interface LikeService {

    void save(Like like);

    Like findById(long id);

    Like updateRating(Like like);

    void deleteById(long id);

    Optional<Like> findByUserIdAndVideoId(long userId, long videoId);
}
