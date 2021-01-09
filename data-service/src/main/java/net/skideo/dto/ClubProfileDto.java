package net.skideo.dto;

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

    public ClubProfileDto(Club club) {
        this.titleClub=club.getTitleClub();
        this.logoLink=club.getLogoLink();
    }

}
