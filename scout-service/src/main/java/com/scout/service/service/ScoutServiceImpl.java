package com.scout.service.service;


import data.service.dao.ScoutDao;
import data.service.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoutServiceImpl implements ScoutService {

    @Autowired
    private ScoutDao scoutDao;

    @Override
    public Scout findByLogin(String login) {
         return scoutDao.findByLogin(login);
    }

    @Override
    public void save(Scout scout) {
        scoutDao.save(scout);
    }
}
