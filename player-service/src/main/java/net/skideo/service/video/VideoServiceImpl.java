package net.skideo.service.video;

import data.service.dao.VideoDao;
import data.service.dto.RatingDto;
import data.service.model.Like;
import data.service.model.User;
import data.service.model.Video;
import data.service.model.enums.Rating;
import data.service.model.enums.RolePeople;
import net.skideo.exception.UserRatedException;
import net.skideo.exception.VideoNotFoundException;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao dao;

    @Autowired
    private UserService userService;

    @Override
    public void save(Video video) {
        dao.save(video);
    }

    @Override
    public Video findById(long id) {
        return dao.findById(id).orElseThrow(
                () ->
                  new VideoNotFoundException("Video not found")
                );
    }

    @Override
    public void updateLink(Video video) {
        Video dbVideo = findById(video.getId());
        dbVideo.setVideoLink(video.getVideoLink());
        save(dbVideo);
    }

    @Override
    public void updateLikes(Video video) {
        Video dbVideo = findById(video.getId());
        dbVideo.setLikes(video.getLikes());
        save(dbVideo);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public void estimateVideo(RatingDto ratingDto) {
        Video video = findById(ratingDto.getIdVideo());
        final User CURRENT_USER = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        for(Like like : video.getLikes()) {
            if(like.getUser().equals(CURRENT_USER)) {
                throw new UserRatedException("User already rated");
            }
        }

        if((CURRENT_USER.getRolePeople()== RolePeople.AMATEUR && video.getUser().getRolePeople()==RolePeople.PROFESSIONAL) ||
           (CURRENT_USER.getRolePeople()== RolePeople.PROFESSIONAL && video.getUser().getRolePeople()==RolePeople.AMATEUR)) {
            Like like = new Like();
            like.setRating(ratingDto.getRating());
            like.setVideo(video);
            like.setUser(CURRENT_USER);
            video.getLikes().add(like);
            updateLikes(video);
        }
    }
}
