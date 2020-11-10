package net.skideo.dto.projections;

import net.skideo.model.Club;

public interface ScoutProfileProjection {

    String getName();

    String getSurname();

    Club getClub();
}
