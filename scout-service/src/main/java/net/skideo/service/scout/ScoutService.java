package net.skideo.service.scout;

import net.skideo.dto.*;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Auth;
import net.skideo.model.Scout;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


public interface ScoutService {

    Scout findById(long id);

    Scout findByLogin(String login);

    void createScout(Scout scout);

    void updateProfile(UpdateProfileDto profile);

    void updateLoginAndPassword(String token,AuthDto authDto);

    void addUserToFavorite(long idUser);

    Page<UserShortInfoClubDto> getFavoriteUsers(Pageable pageable);

    ProfileDto getProfile(long id);

    long getId(String login);

    Scout getCurrentScout();

    String getLoginCurrentScout();

}
