package com.football.manager.service;

import com.football.manager.domain.User;

/**
 * Service for {@link com.football.manager.domain.User}.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

public interface UserService {

    void saveUser(User user);

    User findUserByLogin(String login);
}
