package net.skideo.service.auth;

import net.skideo.dto.AuthDto;
import net.skideo.model.Auth;

public interface AuthService {

    boolean isCorrectPassword(String rowPassword, String encodedPassword);

    boolean isAuthExists(String login);

    void addAuth(Auth auth);

    Auth findByLogin(String login);

    Auth getCurrentAuth(String token);

    void updateLoginAndPassword(String token,AuthDto authDto);

}
