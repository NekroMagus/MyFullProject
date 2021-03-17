package net.skideo.dto.projections;

import net.skideo.model.enums.Role;

public interface UserAuthProjection {

    String getInfoLogin();

    String getInfoPassword();

    Role getRole();
}
