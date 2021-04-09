package net.skideo.dto;

import net.skideo.model.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RatingDto {

    @NotNull
    private long id;
    @NotNull
    private Rating rating;

}
