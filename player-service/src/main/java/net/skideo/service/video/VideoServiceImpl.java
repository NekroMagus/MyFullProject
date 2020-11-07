package net.skideo.service.video;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.ForbiddenException;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.repository.VideoRepository;
import net.skideo.service.like.LikeService;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository repository;
    private final UserService userService;
    private final LikeService likeService;

    @Override
    public void save(Video video) {
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

        if ((user.getRolePeople() == video.getUser().getRolePeople())) {
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
    public Page<VideoDto> findAllMyVideos(long userId, int page, int size) {
        return repository.findAllByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    public void addVideo(String link) {
        User user = userService.getCurrentUser();
        Video video = new Video(link, user);
        save(video);
    }

    @Override
    public Page<VideoDto> findAllAnotherVideos(long userId, int page, int size) {
        return repository.findByUserIdNot(userId, PageRequest.of(page, size));
    }

}
