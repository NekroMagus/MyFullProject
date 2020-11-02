package net.skideo.service.auth;

import net.skideo.dto.UserAuthDto;
import net.skideo.model.User;

public interface AuthService {

     boolean isUserExists(String login);

     boolean isCorrectPassword(UserAuthDto userAuthDto, User user);
}
