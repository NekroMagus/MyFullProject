package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.base.Dto;
import net.skideo.model.User;
import net.skideo.model.Player;

@Data
@NoArgsConstructor
public class UserNSDto extends Dto {

    private String name;
    private String surname;

    public UserNSDto(Player player) {
        this.name= player.getUser().getName();
        this.surname= player.getUser().getSurname();
    }

    public UserNSDto(User user) {
        if(user !=null) {
            this.name = user.getName();
            this.name = user.getSurname();
        }
    }

}
