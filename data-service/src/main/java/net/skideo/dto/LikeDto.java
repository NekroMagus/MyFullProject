package net.skideo.dto;

import lombok.Data;
import net.skideo.model.Like;
import net.skideo.model.enums.Rating;

@Data
public class LikeDto {

    private Long id;
    private Rating rating;
    private UserShortInfoDto author;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.rating = like.getRating();
        this.author = new UserShortInfoDto(like.getUser());
    }
}
