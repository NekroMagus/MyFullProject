package net.skideo.service.user;

import net.skideo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User findById(long id);

}
