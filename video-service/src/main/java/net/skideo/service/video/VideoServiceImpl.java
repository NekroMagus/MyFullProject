package net.skideo.service.video;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.ForbiddenException;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.AlreadyRatedException;
import net.skideo.model.User;
import net.skideo.model.Like;
import net.skideo.model.Video;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.VideoRepository;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository repository;
    private final UserService userService;

    @Override
    public Page<VideoDto> getVideos(int page, int size) {
        User currentUser = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(page,size);
        return repository.findByUserIdNotAndUserServiceRole(currentUser.getId(), ServiceRole.PLAYER,pageable);
    }

    @Override
    public Page<VideoDto> getPopularVideos(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return repository.findPopularVideoByUserServiceRole(ServiceRole.PLAYER,pageable);
    }

    @Override
    public Set<VideoDto> getRandomVideos(int size) {
        User currentUser = userService.getCurrentUser();
        Set<VideoDto> videos = new LinkedHashSet<>();
        List<VideoDto> allVideo = repository.findAllByUserIdNotAndUserServiceRole(currentUser.getId(),ServiceRole.PLAYER);

        for(int i=1;i<=size;i++) {
            int randomIndex = (int) Math.round(Math.random() * (allVideo.size()-1));
            videos.add(allVideo.get(randomIndex));
        }
        return videos;
    }

    @Override
    public Page<VideoDto> getMyVideos(int page, int size) {
        User currentUser = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByUserId(currentUser.getId(),pageable);
    }

    @Override
    public void createVideo(String link) {
        User currentUser = userService.getCurrentUser();
        if(currentUser.getServiceRole()==ServiceRole.PLAYER || currentUser.getServiceRole()==ServiceRole.ACADEMY) {
            Video video = new Video(link, currentUser);
            save(video);
        }
    }

    @Override
    public void estimateVideo(RatingDto dto) {
        Video video = findById(dto.getId());
        User currentUser = userService.getCurrentUser();

        if(isAlreadyLiked(video,currentUser)) {
            throw new AlreadyRatedException("You already liked this video");
        }

        if (currentUser.getRolePeople() == video.getUser().getRolePeople()) {
            throw new ForbiddenException("Player with same rolePlayer cannot like video each other");
        }

        Like newLike = new Like(dto.getRating(),currentUser);

        video.getLikes().add(newLike);

        save(video);
    }

    @Override
    public Video findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Video not found")
        );
    }

    @Override
    public void save(Video video) {
        repository.save(video);
    }

    private boolean isAlreadyLiked(Video video, User currentInfo) {
        for(Like like : video.getLikes()) {
            if(like.getUser().equals(currentInfo)) {
                return true;
            }
        }
        return false;
    }


}
