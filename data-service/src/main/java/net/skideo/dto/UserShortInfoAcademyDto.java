package net.skideo.dto;

import com.sun.istack.internal.logging.Logger;
import lombok.Data;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;

import java.util.List;

@Data
public class UserShortInfoAcademyDto {

    private String name;
    private String surname;
    private RoleFootball roleFootball;

    public UserShortInfoAcademyDto(User players) {
        this.name=players.getInfo().getName();
        this.surname=players.getInfo().getSurname();
        this.roleFootball=players.getInfo().getRoleFootball();
    }

}
