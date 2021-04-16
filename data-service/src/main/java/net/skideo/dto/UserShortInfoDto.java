package net.skideo.dto;

import net.skideo.dto.base.InfoDto;
import net.skideo.model.User;

public class UserShortInfoDto extends InfoDto {

    public UserShortInfoDto(User user) {
        setName(user.getName());
        setSurname(user.getSurname());
    }
}
