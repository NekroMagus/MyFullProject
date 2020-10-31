package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Club;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDto {

    private String name;
    private String surname;
    private String country;
    private RoleFootball roleFootball;
    private Club club;

    public ProfileUserDto(User user) {
        this.name=user.getName();
        this.surname=user.getSurname();
        this.club=user.getClub();
        this.country=user.getCountry();
        this.roleFootball=user.getRoleFootball();
    }

}
