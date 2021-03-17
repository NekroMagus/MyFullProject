package net.skideo.dto;

import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Scout;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String name;
    private String surname;
    private ClubProfileDto club;
    private List<ProfileUserDto> players;

    public ProfileDto(Scout scout) {
        this.name = scout.getInfo().getName();
        this.surname = scout.getInfo().getSurname();
        if(scout.getClub()!=null) {
            this.club = new ClubProfileDto(scout.getClub());
        }
    }

}

