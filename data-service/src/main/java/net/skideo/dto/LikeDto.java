package net.skideo.dto;

import net.skideo.model.Like;
import lombok.Data;

@Data
public class LikeDto {

    private Long id;
    private int rating;
    private UserShortInfoDto author;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.rating = like.getRating().getRating();
        this.author = new UserShortInfoDto(like.getUser());
    }
}
