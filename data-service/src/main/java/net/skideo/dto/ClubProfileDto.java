package net.skideo.dto;

import net.skideo.model.City;
import net.skideo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubProfileDto {

    @NotBlank
    private String titleClub;
    @NotBlank
    private String logoLink;
    @NotBlank
    private City city;

    public ClubProfileDto(Club club) {
        if(club.getUser().getCity()!=null) {
            this.city = club.getUser().getCity();
        }
        this.titleClub=club.getUser().getName();
        this.logoLink=club.getLogoLink();
    }

}
