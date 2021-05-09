package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.base.Dto;
import net.skideo.model.Academy;
import net.skideo.model.City;
import net.skideo.model.Country;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyProfileDto extends Dto {

    private long id;
    @NotBlank
    private String cityName;
    @NotBlank
    private String countryName;
    @NotBlank
    private String titleClub;

    public AcademyProfileDto(Academy academy) {
        this.id=academy.getId();
        this.cityName = academy.getUser().getCity().getName();
        this.countryName = academy.getUser().getCity().getCountry().getName();
        this.titleClub = academy.getUser().getName();
    }

}
