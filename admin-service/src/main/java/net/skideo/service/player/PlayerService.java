package net.skideo.service.player;

import net.skideo.dto.AdminPlayerInfoDto;
import net.skideo.dto.base.SkideoListDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface PlayerService {

    SkideoListDto<AdminPlayerInfoDto> findAllPlayers(int page, int size);

    void loadPlayersCsvFile(String fileName,List<AdminPlayerInfoDto> players) throws IOException;

}
