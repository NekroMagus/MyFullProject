package net.skideo.dto;

import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubProfileDto {

    @NotBlank
    private String titleClub;
    @NotBlank
    private String logoLink;
    @NotBlank
    private String country;
    @NotBlank
    private String city;

    public ClubProfileDto(Club club) {
        if(club.getUser().getCountry()!=null) {
            this.country = club.getUser().getCountry().getName();
        }
        if(club.getUser().getCity()!=null) {
            this.city = club.getUser().getCity().getName();
        }
        this.titleClub=club.getUser().getName();
        this.logoLink=club.getLogoLink();
    }

    public ClubProfileDto(ClubProfileProjection profileProjection) {
        this.logoLink=profileProjection.getLogoLink();
        this.titleClub=profileProjection.getUserName();
        this.country=profileProjection.getUserCountryName();
        this.city=profileProjection.getUserCityName();
    }

}
