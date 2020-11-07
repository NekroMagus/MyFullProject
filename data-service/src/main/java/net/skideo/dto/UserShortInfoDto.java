package net.skideo.dto;

import lombok.Data;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;

@Data
public class UserShortInfoDto {

    private long id;
    private String name;
    private String surname;
    private RoleFootball roleFootball;


    public UserShortInfoDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.roleFootball = user.getRoleFootball();
    }
}
