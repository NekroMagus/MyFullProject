package net.skideo.service.auth;

public interface AuthService {

     boolean isUserExists(String login);

     boolean isPasswordMatch(String rowPassword, String encodedPassword);
}
