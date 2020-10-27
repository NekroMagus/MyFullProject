package net.skideo.service.video;

import net.skideo.dao.LikeDao;
import net.skideo.dao.VideoDao;
import net.skideo.dto.RatingDto;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.model.enums.RolePeople;
import net.skideo.exception.UserRatedException;
import net.skideo.exception.VideoNotFoundException;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class VideoServiceImpl implements VideoService {

    Logger log = Logger.getLogger(VideoServiceImpl.class.getName());

    @Autowired
    private VideoDao dao;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeDao likeDao;

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

        for (Like like : video.getLikes()) {
            if (like.getUser().equals(CURRENT_USER)) {
                throw new UserRatedException("User already rated");
            }
        }

        if ((CURRENT_USER.getRolePeople() == RolePeople.AMATEUR && video.getUser().getRolePeople() == RolePeople.PROFESSIONAL) ||
                (CURRENT_USER.getRolePeople() == RolePeople.PROFESSIONAL && video.getUser().getRolePeople() == RolePeople.AMATEUR)) {

            Like like = new Like();
            like.setRating(ratingDto.getRating());
            like.setVideo(video);
            like.setUser(CURRENT_USER);
            likeDao.save(like);

            if(video.getLikes()==null) {
                video.setLikes(new LinkedHashSet<>());
            }
            video.getLikes().add(like);

            updateLikes(video);

        }
    }

    @Override
    public int getRating(long idVideo) {
        int rating = 0;
        Video video = findById(idVideo);
        for (Like like : video.getLikes()) {
            rating += like.getRating().getRating();
        }
        return rating / video.getLikes().size();
    }

    public List<Video> getVideos(User user) {
        List<Video> allVideos = dao.findAll();
        List<Video> videos = new LinkedList<>();
        for(Video video : allVideos) {
            if(video.getUser()!=null && video.getUser().equals(user)) {
                videos.add(video);
            }
        }
        return videos;
    }

}
