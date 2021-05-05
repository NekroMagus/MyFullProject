package net.skideo.dto.shortInfo;

import lombok.Data;
import net.skideo.model.Player;
import net.skideo.model.enums.RoleFootball;

@Data
public class PlayerShortInfoDto {

    private long id;
    private String name;
    private String surname;
    private RoleFootball roleFootball;

    public PlayerShortInfoDto(Player player) {
        this.id=player.getId();
        this.name=player.getUser().getName();
        this.surname=player.getUser().getSurname();
        this.roleFootball=player.getRoleFootball();
    }

}
