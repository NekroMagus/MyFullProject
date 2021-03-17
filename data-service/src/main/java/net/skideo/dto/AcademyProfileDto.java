package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Academy;

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

    public AcademyProfileDto(Academy academy) {
        this.city = academy.getInfo().getCity();
        this.country = academy.getInfo().getCountry();
        this.titleClub = academy.getInfo().getName();
    }

}
