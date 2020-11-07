package net.skideo.dto.projections;

import net.skideo.dto.ClubProfileDto;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import java.time.LocalDate;

public interface UserProfileProjection {

    Long getId();

    RoleFootball getRoleFootball();

    String getName();

    String getSurname();

    String getCountry();

    String getCity();

    LeadingLeg getLeadingLeg();

    RolePeople getRolePeople();

    ClubProfile getClub();

    boolean isHasAgent();

    LocalDate getBirthDate();

    interface ClubProfile {
       String getTitleClub();
       String getLogoLink();
    }
}
