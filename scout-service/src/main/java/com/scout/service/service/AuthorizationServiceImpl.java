package com.scout.service.service;

import data.service.dao.ScoutDao;
import data.service.dto.AuthDto;
import data.service.model.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private ScoutDao scoutDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isScoutExists(String login) {
        return scoutDao.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(AuthDto authDto, Scout scout) {
        return scout!=null && encoder.matches(scout.getPassword(),authDto.getPassword());
    }
}
