package net.skideo.service.like;

import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import lombok.RequiredArgsConstructor;
import net.skideo.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;

    @Override
    public void save(Like like) {
        repository.save(like);
    }

    @Override
    public Like findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Like not found"));
    }

    @Override
    public Like updateRating(Like like) {
        Like dbLike = findById(like.getId());
        dbLike.setRating(like.getRating());
        repository.save(dbLike);
        return dbLike;
    }

    @Override
    public void deleteById(long id) {
         repository.deleteById(id);
    }

    @Override
    public Optional<Like> findByInfoIdAndVideoId(long userId, long videoId) {
        return repository.findByInfoIdAndVideoId(userId, videoId);
    }
}
