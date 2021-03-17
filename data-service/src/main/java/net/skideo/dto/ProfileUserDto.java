package net.skideo.dto;

import net.skideo.dto.projections.ProfileProjection;
import net.skideo.model.Club;
import net.skideo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.RoleFootball;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDto {

    private long id;
    private String name;
    private String surname;
    private String country;
    private RoleFootball roleFootball;
    private ClubProfileDto club;

    public ProfileUserDto(User user) {
        this.id = user.getId();
        this.name = user.getInfo().getName();
        this.surname = user.getInfo().getSurname();
        this.roleFootball = user.getInfo().getRoleFootball();
        this.club = new ClubProfileDto(user.getClub());
    }

}
