package net.skideo.dto.projections;

import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface UserProfileProjection {

    Long getId();

    RoleFootball getInfoRoleFootball();

    String getInfoName();

    String getInfoSurname();

    String getInfoCountry();

    String getInfoCity();

    LeadingLeg getLeadingLeg();

    RolePeople getRolePeople();

    ClubProfile getClub();

    boolean isHasAgent();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate getBirthDate();

    interface ClubProfile {
       String getTitleClub();
       String getLogoLink();
    }
}
