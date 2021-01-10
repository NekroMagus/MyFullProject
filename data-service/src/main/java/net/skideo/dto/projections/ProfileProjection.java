package net.skideo.dto.projections;

import net.skideo.model.Club;
import net.skideo.model.enums.RoleFootball;

public interface ProfileProjection {

    Long getId();

    String getInfoName();

    String getInfoSurname();

    String getInfoCountry();

    RoleFootball getInfoRoleFootball();

    Club getClub();

}
