package net.skideo.dto;

import net.skideo.dto.base.Dto;
import net.skideo.model.City;
import net.skideo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubProfileDto extends Dto {

    @NotBlank
    private String titleClub;
    @NotBlank
    private String logoLink;
    @NotBlank
    private String cityName;
    @NotBlank
    private String countryName;

    public ClubProfileDto(Club club) {
        if(club.getUser().getCity()!=null) {
            this.cityName = club.getUser().getCity().getName();
            this.countryName = club.getUser().getCity().getCountry().getName();
        }
        this.titleClub=club.getUser().getName();
        this.logoLink=club.getLogoLink();
    }

}
