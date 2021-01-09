package net.skideo.dto;

import net.skideo.model.Like;
import net.skideo.model.enums.Rating;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LikeDto {


    private Long id;

    private Rating rating;

    private UserShortInfoDto author;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.rating = like.getRating();
        this.author = new UserShortInfoDto(like.getUser().getInfo());
    }
}
