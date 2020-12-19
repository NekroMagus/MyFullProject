package net.skideo.service.user;

import net.skideo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUserById(long id);

    Page<User> getAmateurPlayers(Pageable pageable);

}
