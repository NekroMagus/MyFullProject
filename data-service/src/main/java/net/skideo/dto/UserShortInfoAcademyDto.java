package net.skideo.dto;


import net.skideo.dto.base.InfoDto;
import net.skideo.model.Player;

public class UserShortInfoAcademyDto extends InfoDto {

    public UserShortInfoAcademyDto(Player players) {
        setName(players.getInfo().getName());
        setSurname(players.getInfo().getSurname());
        setRoleFootball(players.getInfo().getRoleFootball());
    }

}
