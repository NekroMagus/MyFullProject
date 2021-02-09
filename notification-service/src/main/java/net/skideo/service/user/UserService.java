package net.skideo.service.user;

import net.skideo.model.User;

public interface UserService {

    User getUserById(long idUser);

    void save(User user);

    User getCurrentUser();

}
