package net.skideo.dto;

import net.skideo.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class VideoDto {

    private String name;
    private String surname;
    private String video;
    private long id;

    public VideoDto(User user, String video) {
        this.name=user.getName();
        this.surname=user.getSurname();
        this.video=video;
        this.id=user.getId();
    }

}
