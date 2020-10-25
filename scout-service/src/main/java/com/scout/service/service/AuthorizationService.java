package com.scout.service.service;

import data.service.dto.AuthDto;
import data.service.model.Scout;

public interface AuthorizationService {

    boolean isScoutExists(String login);

    boolean isCorrectPassword(AuthDto regDto, Scout user);

}
