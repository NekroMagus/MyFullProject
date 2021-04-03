package net.skideo.service.player;

import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoAcademyDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Player getUserById(long id);

    void addPlayer(long id);

    Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable);

    Page<UserShortInfoAcademyDto> getMyPlayers(Pageable pageable);

    Page<UserNSDto> findUsersByNameAndSurname(String name, String surname,int page,int size);

}
