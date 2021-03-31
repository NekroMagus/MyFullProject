package net.skideo.dto;

import net.skideo.dto.base.InfoDto;
import net.skideo.model.Info;
import lombok.Data;
import net.skideo.model.enums.RoleFootball;

public class UserShortInfoDto extends InfoDto {

    public UserShortInfoDto(Info info) {
        setName(info.getName());
        setSurname(info.getSurname());
        setRoleFootball(info.getRoleFootball());
    }
}
