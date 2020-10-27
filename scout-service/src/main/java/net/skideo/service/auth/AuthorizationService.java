package net.skideo.service.auth;

import net.skideo.dto.AuthDto;
import net.skideo.model.Scout;

public interface AuthorizationService {

    boolean isScoutExists(String login);

    boolean isCorrectPassword(AuthDto regDto, Scout user);

}
