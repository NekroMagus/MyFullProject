package net.skideo.service.video;

import net.skideo.model.Video;
import lombok.RequiredArgsConstructor;
import net.skideo.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public List<Video> findAllByUserId(long id) {
        return videoRepository.findAllByUserId(id);
    }
}
