package com.scout.service.service;

import data.service.model.Scout;

public interface ScoutService {

    Scout findByLogin(String login);

    void save(Scout scout);
}
