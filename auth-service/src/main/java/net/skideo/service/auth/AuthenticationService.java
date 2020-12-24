package net.skideo.service.auth;

import net.skideo.model.Auth;

public interface AuthenticationService {

    boolean isCorrectPassword(String rowPassword, String encodedPassword);

    boolean isAuthExists(String login);

    void addAuth(Auth auth);

    Auth findByLogin(String login);

}
