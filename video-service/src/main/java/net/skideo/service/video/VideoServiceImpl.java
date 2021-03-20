package net.skideo.service.video;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.RatingDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.ForbiddenException;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.AlreadyRatedException;
import net.skideo.model.Info;
import net.skideo.model.Like;
import net.skideo.model.Video;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.LikeRepository;
import net.skideo.repository.VideoRepository;
import net.skideo.service.info.InfoService;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository repository;
    private final LikeRepository likeRepository;
    private final InfoService infoService;
    private final UserService userService;

    Logger log = Logger.getLogger(VideoServiceImpl.class.getName());

    @Override
    @Transactional
    public Page<VideoDto> getVideos(int page, int size) {
        Info currentInfo = infoService.getCurrentInfo();
        Pageable pageable = PageRequest.of(page,size);
        return repository.findByInfoIdNotAndInfoServiceRole(currentInfo.getId(), ServiceRole.USER,pageable);
    }

    @Override
    @Transactional
    public Page<VideoDto> getPopularVideos(int page, int size) {
        Info currentInfo = infoService.getCurrentInfo();
        Pageable pageable = PageRequest.of(page,size, Sort.by("rating"));
        return repository.findByInfoIdNotAndInfoServiceRole(currentInfo.getId(),ServiceRole.USER,pageable);
    }

    @Override
    @Transactional
    public Set<VideoDto> getRandomVideos(int size) {
        Info currentInfo = infoService.getCurrentInfo();
        Set<VideoDto> videos = new LinkedHashSet<>();
        List<VideoDto> allVideo = repository.findAllByInfoIdNotAndInfoServiceRole(currentInfo.getId(),ServiceRole.USER);

        for(int i=1;i<=size;i++) {
            int randomIndex = (int) Math.round(Math.random() * (allVideo.size()-1));
            videos.add(allVideo.get(randomIndex));
        }
        return videos;
    }

    @Override
    @Transactional
    public Page<VideoDto> getMyVideos(int page, int size) {
        Info currentInfo = infoService.getCurrentInfo();
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByInfoId(currentInfo.getId(),pageable);
    }

    @Override
    public void createVideo(String link) {
        Info currentInfo = infoService.getCurrentInfo();
        Video video = new Video(link,currentInfo);
        save(video);
    }

    @Override
    public void estimateVideo(RatingDto dto) {
        Video video = findById(dto.getId());
        Info currentInfo = infoService.getCurrentInfo();

        if(isAlreadyLiked(video,currentInfo)) {
            throw new AlreadyRatedException("You already liked this video");
        }

        if ((currentInfo.getRolePeople() == video.getInfo().getRolePeople())) {
            throw new ForbiddenException("Player with same rolePlayer cannot like video each other");
        }

        Like newLike = new Like(dto.getRating(),currentInfo);

        video.setRating((video.getLikes().size() * video.getRating() + dto.getRating().getRating()) / (video.getLikes().size()+1));
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

    private boolean isAlreadyLiked(Video video, Info currentInfo) {
        for(Like like : video.getLikes()) {
            if(like.getInfo().equals(currentInfo)) {
                return true;
            }
        }
        return false;
    }


}
