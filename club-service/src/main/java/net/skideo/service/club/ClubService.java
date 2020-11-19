package net.skideo.service.club;

import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.dto.projections.ClubPasswordProjection;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.model.Club;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClubService {

    Club findByLogin(String login);


    ClubPasswordProjection getPasswordByLogin(String login);

    ClubProfileProjection getProfileByLogin(String login);

    Club findById(long id);

    void save(Club club);

    ClubProfileDto getProfile();

    Page<ScoutDto> getScouts(Club currentClub,int page,int size);

    void addScout(long id,Club currentClub);

    void removeScout(long id,Club currentClub);

    void setRegionScout(long id, String region,Club currentClub);

    Page<ScoutDto> getScoutsByRegion(String region, long idCurrentClub,int page,int size);

    void addUserToFavorite(long idUser,Club club);

    List<VideoDto> findVideos(int page,int size);


}
