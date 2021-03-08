package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Info;
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

    public UserNSDto(Info info) {
        if(info!=null) {
            this.name = info.getName();
            this.name = info.getSurname();
        }
    }

}
