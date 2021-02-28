package net.skideo.dto;

import net.skideo.model.Info;
import net.skideo.model.User;
import lombok.Data;
import net.skideo.model.enums.RoleFootball;

@Data
public class UserShortInfoDto {

    private String name;
    private String surname;
    private RoleFootball roleFootball;


    public UserShortInfoDto(Info info) {
        this.name = info.getName();
        this.surname = info.getSurname();
        this.roleFootball = info.getRoleFootball();
    }
}
