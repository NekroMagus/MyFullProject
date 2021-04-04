package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String city;
    @NotBlank
    private String country;

}
