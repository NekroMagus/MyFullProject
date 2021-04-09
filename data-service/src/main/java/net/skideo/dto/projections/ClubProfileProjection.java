package net.skideo.dto.projections;

import net.skideo.model.City;

public interface ClubProfileProjection {

    String getLogoLink();

    String getUserName();

    City getUserCity();

}
