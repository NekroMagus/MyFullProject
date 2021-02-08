package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;

@Data
@NoArgsConstructor
public class UserNSDto {

    private String name;
    private String surname;

    public UserNSDto(User user) {
        this.name=user.getInfo().getName();
        this.surname=user.getInfo().getSurname();
    }

}
