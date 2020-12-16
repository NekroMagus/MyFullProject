package net.skideo.service.scout;

import net.skideo.dto.ProfileDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Scout;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import java.time.LocalDate;
import java.util.List;


public interface ScoutService {

    Scout findById(long id);

    Scout findByLogin(String login);

    PasswordProjection getPasswordByLogin(String login);

    ScoutProfileProjection getProfileByLogin(String login);

    void save(Scout scout);

    ProfileDto getProfile(ScoutProfileProjection currentScout);

    void updateProfile(UpdateProfileDto profile);

    List<SearchDto> search(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate dateOfBirth, int page, int size);

    void addUserToFavorite(long idUser,Scout currentScout);

}
