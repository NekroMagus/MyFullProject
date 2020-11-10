package net.skideo.service.club;

import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.dto.projections.ClubPasswordProjection;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.dto.projections.UserProfileProjection;
import net.skideo.model.Club;
import java.util.List;

public interface ClubService {

    Club findByLogin(String login);

    ClubPasswordProjection getPasswordByLogin(String login);

    ClubProfileProjection getProfileByLogin(String login);

    Club findById(long id);

    void save(Club club);

    ClubProfileDto getProfile();

    List<ScoutDto> getScouts(Club currentClub);

    void addScout(long id,Club currentClub);

    void removeScout(long id,Club currentClub);

    void setRegionScout(long id, String region,Club currentClub);

    List<ScoutDto> getScoutsByRegion(String region,Club currentClub);

    void addUserToFavorite(long idUser,Club club);

    List<VideoDto> findVideos(int page,int size);
}
