package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Scout;

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
        this.name = scout.getUser().getName();
        this.surname = scout.getUser().getSurname();
        if(scout.getClub()!=null) {
            this.club = new ClubProfileDto(scout.getClub());
        }
    }

}

