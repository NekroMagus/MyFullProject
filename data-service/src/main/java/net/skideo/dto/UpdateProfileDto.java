package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.City;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private City city;

}
