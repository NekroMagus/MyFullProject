package com.football.manager.service.impl;

import com.football.manager.dao.VideoDao;
import com.football.manager.model.Video;
import com.football.manager.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link com.football.manager.service.VideoService} interface.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    public void addVideo(Video video) {
        videoDao.save(video);
    }
}
