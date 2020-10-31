package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Club;
import net.skideo.model.Scout;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

     private String name;
     private String surname;
     private Club club;
     private List<ProfileUserDto> players;

     public ProfileDto(Scout scout) {
          this.name=scout.getName();
          this.surname=scout.getSurname();
          this.club=scout.getClub();
     }

}
