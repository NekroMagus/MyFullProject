package net.skideo.service.user;

import net.skideo.model.User;
import net.skideo.model.abstracts.AbstractUserEntity;

public interface UserService {

    void save(User user);

    User getCurrentUser();

}
