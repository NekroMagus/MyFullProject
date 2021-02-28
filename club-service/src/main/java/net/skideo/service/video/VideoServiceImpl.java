package net.skideo.service.video;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.VideoDto;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public Page<VideoDto> findVideos(Pageable pageable) {
        return videoRepository.findAllByInfoServiceRole(ServiceRole.USER,pageable);
    }
}
