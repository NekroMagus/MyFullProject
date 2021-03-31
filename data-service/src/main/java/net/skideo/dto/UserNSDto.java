package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Info;
import net.skideo.model.Player;

@Data
@NoArgsConstructor
public class UserNSDto {

    private String name;
    private String surname;

    public UserNSDto(Player player) {
        this.name= player.getInfo().getName();
        this.surname= player.getInfo().getSurname();
    }

    public UserNSDto(Info info) {
        if(info!=null) {
            this.name = info.getName();
            this.name = info.getSurname();
        }
    }

}
