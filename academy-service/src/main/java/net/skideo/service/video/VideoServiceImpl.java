package net.skideo.service.video;

import net.skideo.dto.VideoDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Academy;
import net.skideo.model.Video;
import net.skideo.repository.VideoRepository;
import net.skideo.service.academy.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;
    @Autowired
    private AcademyService academyService;

    Logger log = Logger.getLogger(VideoServiceImpl.class.getName());

    @Override
    public void create(Video video) {
        repository.save(video);
    }

    @Override
    public Page<VideoDto> getMyVideos(int page, int size) {
        IdProjection idProjection = academyService.getIdCurrentAcademy();
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByInfoId(idProjection.getInfoId(),pageable);
    }
}
