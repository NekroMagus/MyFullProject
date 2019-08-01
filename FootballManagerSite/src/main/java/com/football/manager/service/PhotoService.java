package com.football.manager.service;

import com.football.manager.dao.PhotoDao;
import com.football.manager.domain.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private PhotoDao photoDao;

    public void saveFile(Photo photo){
        photoDao.save(photo);
    }
}
