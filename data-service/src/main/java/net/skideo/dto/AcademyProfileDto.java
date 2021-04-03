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

    private long id;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String titleClub;

    public AcademyProfileDto(Academy academy) {
        this.id=academy.getId();
        this.city = academy.getUser().getCity();
        this.country = academy.getUser().getCountry();
        this.titleClub = academy.getUser().getName();
    }

}
