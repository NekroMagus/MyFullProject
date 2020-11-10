package net.skideo.service.auth;


public interface AuthorizationService {

    boolean isScoutExists(String login);

    boolean isCorrectPassword(String rowPassword,String encodedPassword);

}
