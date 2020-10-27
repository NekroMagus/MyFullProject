package net.skideo.service.scout;

import net.skideo.dto.ProfileDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.model.Scout;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import java.time.LocalDate;
import java.util.List;


public interface ScoutService {

    Scout findById(long id);

    Scout findByLogin(String login);

    void save(Scout scout);

    ProfileDto getProfile();

    void updateProfile(UpdateProfileDto profile);

    List<SearchDto> search(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate dateOfBirth);

    void addUserToFavorite(long idUser);
}
