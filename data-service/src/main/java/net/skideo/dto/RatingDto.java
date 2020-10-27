package net.skideo.dto;

import net.skideo.model.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDto {

    private long idVideo;
    private Rating rating;

}
