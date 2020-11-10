package net.skideo.service.auth;

import net.skideo.dto.AuthDto;
import net.skideo.model.Club;
import net.skideo.model.Scout;

public interface AuthorizationService {

    boolean isScoutExists(String login);

    boolean isCorrectPassword(String rowPassword,String encodedPassword);
}
