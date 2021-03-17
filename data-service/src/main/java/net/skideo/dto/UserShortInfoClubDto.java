package net.skideo.dto;

import lombok.Data;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;

@Data
public class UserShortInfoClubDto {

    private String name;
    private String surname;
    private RoleFootball roleFootball;

    public UserShortInfoClubDto(User favoriteUsers) {
        this.name=favoriteUsers.getInfo().getName();
        this.surname=favoriteUsers.getInfo().getSurname();
        this.roleFootball=favoriteUsers.getInfo().getRoleFootball();
    }

}
