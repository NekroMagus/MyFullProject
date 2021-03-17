package net.skideo.dto;

import net.skideo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private String name;
    private String surname;

    public SearchDto(User user) {
        this.name=user.getInfo().getName();
        this.surname=user.getInfo().getSurname();
    }

}
