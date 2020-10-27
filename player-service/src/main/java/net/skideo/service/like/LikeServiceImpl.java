package net.skideo.service.like;

import net.skideo.dao.LikeDao;
import net.skideo.model.Like;
import net.skideo.exception.LikeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDao dao;

    @Override
    public void save(Like like) {
        dao.save(like);
    }

    @Override
    public Like findById(long id) {
        return dao.findById(id).orElseThrow(
                () ->
                   new LikeNotFoundException("Like not found")
                );
    }

    @Override
    public void updateRating(Like like) {
        Like dbLike = findById(like.getId());
       // dbLike.setRating(like.getRating());
        dao.save(dbLike);
    }

    @Override
    public void deleteById(long id) {
         dao.deleteById(id);
    }
}
