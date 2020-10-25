package data.service.dto;

import data.service.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class VideoDto {

    private String name;
    private String surname;
    private String video;
    private long id;

    public VideoDto(User user) {
        this.name=user.getName();
        this.surname=user.getSurname();
      //  this.video=user.getVideo();
        this.id=user.getId();
    }

}
