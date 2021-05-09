package net.skideo.dto;

import net.skideo.dto.base.Dto;
import net.skideo.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto extends Dto {

    private String name;
    private String surname;

    public SearchDto(Player player) {
        this.name= player.getUser().getName();
        this.surname= player.getUser().getSurname();
    }

}
