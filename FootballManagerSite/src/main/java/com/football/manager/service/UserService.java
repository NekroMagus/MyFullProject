package com.football.manager.service;

import com.football.manager.model.User;

import java.util.List;

/**
 * Service for {@link com.football.manager.model.User}.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

public interface UserService {

    void saveUser(User user);

    void updateUser(User user);

    User findUserByEmail(String email);

    List<User> findUsersByName(String name);
}
