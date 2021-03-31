package net.skideo.dto;

import net.skideo.dto.base.InfoDto;
import net.skideo.model.Player;

public class UserShortInfoClubDto extends InfoDto {

    public UserShortInfoClubDto(Player favoriteUsers) {
        setName(favoriteUsers.getInfo().getName());
        setSurname(favoriteUsers.getInfo().getSurname());
        setRoleFootball(favoriteUsers.getInfo().getRoleFootball());
    }

}
