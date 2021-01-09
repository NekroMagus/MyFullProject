package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyProfileDto {

    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String titleClub;

}
