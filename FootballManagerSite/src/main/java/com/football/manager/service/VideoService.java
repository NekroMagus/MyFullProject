package com.football.manager.service;

import com.football.manager.dao.VideoDao;
import com.football.manager.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;

public class VideoService {
    @Autowired
    private VideoDao videoDao;

    public void addVideo(Video video) {
        videoDao.save(video);
    }
}
