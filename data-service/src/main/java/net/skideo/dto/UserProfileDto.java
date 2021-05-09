package net.skideo.dto;

import net.skideo.dto.base.Dto;
import net.skideo.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto extends Dto {

    private RoleFootball roleFootball;
    private String name;
    private String surname;
    private String country;
    private String city;
    private LeadingLeg leadingLeg;
    private RolePeople rolePeople;
    private ClubProfileDto club;
    private boolean agent;
    private LocalDate birthDate;

    public UserProfileDto(Player player) {
        this.roleFootball = player.getRoleFootball();
        this.name = player.getUser().getName();
        this.surname = player.getUser().getSurname();
        if(player.getUser().getCity()!=null && player.getUser().getCity().getCountry()!=null) {
            this.country = player.getUser().getCity().getCountry().getName();
        }
        if(player.getUser().getCity()!=null) {
            this.city = player.getUser().getCity().getName();
        }
        this.leadingLeg = player.getLeadingLeg();
        this.rolePeople = player.getRolePeople();
        if(player.getClub()!=null) {
            this.club = new ClubProfileDto(player.getClub());
        }
        this.agent = player.getHasAgent();
        this.birthDate = player.getBirthDate();
    }

}
