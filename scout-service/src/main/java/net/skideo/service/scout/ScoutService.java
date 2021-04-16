package net.skideo.service.scout;

import net.skideo.dto.*;
import net.skideo.model.Scout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScoutService {

    Scout findById(long id);

    Scout findByLogin(String login);

    void createScout(Scout scout);

    void updateProfile(UpdateProfileDto profile);

    void updateLoginAndPassword(AuthDto authDto);

    void addUserToFavorite(long idUser);

    Page<UserShortInfoClubDto> getFavoriteUsers(Pageable pageable);

    ProfileDto getProfile(long id);

    long getIdByLogin(String login);

    Scout getCurrentScout();

    String getLoginCurrentScout();

}
