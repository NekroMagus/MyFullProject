package net.skideo.service.video;

import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.like.LikeService;
import net.skideo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.skideo.exception.ForbiddenException;
import net.skideo.repository.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository repository;
    private final UserService userService;
    private final LikeService likeService;

    Logger log = Logger.getLogger(VideoServiceImpl.class.getName());

    private void save(Video video) {
        repository.save(video);
    }

    @Override
    public Video findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video with id " + id + " not found"));
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
        repository.deleteById(id);
    }

    @Override
    public void estimateVideo(RatingDto dto, User user) {
        Video video = findById(dto.getIdVideo());

        if ((user.getInfo().getRolePeople() == video.getInfo().getRolePeople())) {
            throw new ForbiddenException("Player with same rolePlayer cannot like video each other");
        }
        Like like = new Like();
        like.setRating(dto.getRating());
        like.setVideo(video);
        like.setUser(user);
        likeService.save(like);
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

    @Override
    @Transactional
    public Page<VideoDto> findAllMyVideos(long idInfo, int page, int size) {
        return repository.findAllByInfoId(idInfo, PageRequest.of(page, size));
    }

    @Override
    public void addVideo(String link) {
        User user = userService.getCurrentUser();
        Video video = new Video(link, user.getInfo());
        save(video);
    }

    @Override
    public Page<VideoDto> findAllAnotherVideos(long idInfo, int page, int size) {
        return repository.findByInfoIdNotAndInfoServiceRole(idInfo, ServiceRole.USER, PageRequest.of(page, size));
    }

    @Override
    public void updateComments(Video video) {
        Video dbVideo = findById(video.getId());

        dbVideo.setComments(video.getComments());

        save(dbVideo);
    }

}
