package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;
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
    private LocalDate dateOfBirth;

    public UserProfileDto(User user) {
        this.roleFootball=user.getRoleFootball();
        this.name=user.getName();
        this.surname=user.getSurname();
        this.city=user.getCity();
        this.country=user.getCountry();
        this.leadingLeg=user.getLeadingLeg();
        this.rolePeople=user.getRolePeople();
        this.club=new ClubProfileDto(user.getClub());
        this.agent=user.isAgent();
        this.dateOfBirth=user.getDateOfBirth();
    }

}
