package net.skideo.dto.shortInfo;

import lombok.Data;
import net.skideo.model.Academy;
import net.skideo.model.Club;

@Data
public class AcademyAndClubShortInfoDto {

    private long id;
    private String title;
    private String city;
    private String country;

    public AcademyAndClubShortInfoDto(Academy academy) {
        this.id=academy.getId();
        this.title=academy.getUser().getName();
        this.city=academy.getUser().getCity().getName();
        this.country=academy.getUser().getCity().getCountry().getName();
    }

    public AcademyAndClubShortInfoDto(Club club) {
        this.id=club.getId();
        this.title=club.getUser().getName();
        this.city=club.getUser().getCity().getName();
        this.country=club.getUser().getCity().getCountry().getName();
    }

}
