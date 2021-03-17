package net.skideo.service.user;

import net.skideo.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    List<User> findAll();

}
