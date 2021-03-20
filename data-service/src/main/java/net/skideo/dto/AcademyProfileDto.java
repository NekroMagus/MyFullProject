package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Academy;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
        this.city = academy.getInfo().getCity();
        this.country = academy.getInfo().getCountry();
        this.titleClub = academy.getInfo().getName();
    }

}
