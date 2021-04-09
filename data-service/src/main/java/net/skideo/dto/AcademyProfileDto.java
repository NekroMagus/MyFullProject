package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Academy;
import net.skideo.model.City;
import net.skideo.model.Country;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyProfileDto {

    private long id;
    @NotBlank
    private City city;
    @NotBlank
    private String titleClub;

    public AcademyProfileDto(Academy academy) {
        this.id=academy.getId();
        this.city = academy.getUser().getCity();
        this.titleClub = academy.getUser().getName();
    }

}
