package net.skideo.dto;

import net.skideo.model.Player;
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

    public ProfileUserDto(Player player) {
        this.id = player.getId();
        this.name = player.getUser().getName();
        this.surname = player.getUser().getSurname();
        this.roleFootball = player.getRoleFootball();
        this.club = new ClubProfileDto(player.getClub());
    }

}
