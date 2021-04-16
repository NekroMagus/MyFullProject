package net.skideo.dto;

import net.skideo.dto.base.InfoDto;
import net.skideo.model.Player;

public class UserShortInfoClubDto extends InfoDto {

    public UserShortInfoClubDto(Player favoriteUsers) {
        setName(favoriteUsers.getUser().getName());
        setSurname(favoriteUsers.getUser().getSurname());
        setRoleFootball(favoriteUsers.getRoleFootball());
    }

}
