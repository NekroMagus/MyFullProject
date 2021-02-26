package net.skideo.service.club;

import net.skideo.dto.AuthDto;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.dto.projections.IdProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Club;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClubService {

    Club findByLogin(String login);

    Club findById(long id);

    void save(Club club);

    ClubProfileDto getProfile();

    void updateProfile(ClubProfileDto profile);

    void updateLoginAndPassword(AuthDto authDto);

    void addUserToFavorite(long idUser);

    List<VideoDto> findVideos(int page,int size);

    Club getCurrentClub();

    IdProjection getIdCurrentClub();

}
