package com.football.manager.service.impl;

import com.football.manager.dao.PhotoDao;
import com.football.manager.model.Photo;
import com.football.manager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link com.football.manager.service.PhotoService} interface.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    public void addPhoto(Photo photo){
        photoDao.save(photo);
    }
}