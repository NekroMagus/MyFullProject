package net.skideo.service.auth;

public interface AuthService {

    boolean isCorrectPassword(String rowPassword, String encodedPassword);

    boolean isAcademyExists(String login);

}
