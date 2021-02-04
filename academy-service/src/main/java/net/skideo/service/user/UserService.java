package net.skideo.service.user;

import net.skideo.dto.UserShortInfoDto;
import net.skideo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUserById(long id);

    Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable);

    Page<UserShortInfoDto> getPlayers(Pageable pageable);

    Page<User> findUsersByNameAndSurname(String name,String surname);

}
