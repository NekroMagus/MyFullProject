package net.skideo.service.club;

import net.skideo.dto.*;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.dto.projections.IdProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClubService {

    Club findByLogin(String login);

    void save(Club club);

    ClubProfileDto getProfile(long id);

    void updateProfile(ClubProfileDto profile);

    void updateLoginAndPassword( AuthDto authDto);

    void addUserToFavorite(long idUser);

    Page<UserShortInfoClubDto> getFavoriteUsers(Pageable pageable);

    long getIdByLogin(String login);

    Club getCurrentClub();

}
