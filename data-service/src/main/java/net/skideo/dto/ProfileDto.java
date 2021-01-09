package net.skideo.dto;

import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String name;
    private String surname;
    private Club club;
    private List<ProfileUserDto> players;

    public ProfileDto(ScoutProfileProjection scout) {
        this.name = scout.getName();
        this.surname = scout.getSurname();
        this.club = scout.getClub();
    }

}

