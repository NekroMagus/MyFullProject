package net.skideo.service.club;

import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Club;
import java.util.List;

public interface ClubService {

    Club findByLogin(String login);

    void save(Club club);

    ClubProfileDto getProfile();

    List<ScoutDto> getScouts();

    void addScout(long id);

    void removeScout(long id);

    void setRegionScout(long id, String region);

    List<ScoutDto> getScoutsByRegion(String region);

    void addUserToFavorite(long idUser);

    List<VideoDto> findVideos();
}
