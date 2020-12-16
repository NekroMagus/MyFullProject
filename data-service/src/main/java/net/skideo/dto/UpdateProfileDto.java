package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDto {

    @NotNull
    @Min(2)
    private String name;
    @NotNull
    @Min(2)
    private String surname;

}
