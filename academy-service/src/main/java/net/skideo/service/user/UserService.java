package net.skideo.service.user;

import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.model.Notification;
import net.skideo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable);

    Page<UserShortInfoDto> getPlayers(Pageable pageable);

    Page<UserNSDto> findUsersByNameAndSurname(String name, String surname,int page,int size);

}
