package net.skideo.service.player;

import net.skideo.dto.AdminPlayerInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlayerService {

    List<AdminPlayerInfoDto> findAllPlayers(int page, int size);

}
