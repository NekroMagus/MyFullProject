package net.skideo.dto;

import net.skideo.model.User;
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
public class UserProfileDto {

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

    public UserProfileDto(User user) {
        this.roleFootball = user.getInfo().getRoleFootball();
        this.name = user.getInfo().getName();
        this.surname = user.getInfo().getSurname();
        this.city = user.getInfo().getCity();
        this.country = user.getInfo().getCountry();
        this.leadingLeg = user.getLeadingLeg();
        this.rolePeople = user.getInfo().getRolePeople();
        this.club = new ClubProfileDto(user.getClub());
        this.agent = user.isHasAgent();
        this.birthDate = user.getBirthDate();
    }

}
