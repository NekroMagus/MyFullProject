package net.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.skideo.model.Like;
import net.skideo.model.Video;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RolePeople;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String surname;
    private RoleFootball roleFootball;
    private String login;
    private String email;
    private String telephoneNumber;
    private LocalDate dateOfBirth;
    private String country;
    private String city;
    private String socialNetwork;
    private String video;
    private LeadingLeg leadingLeg;
    private RolePeople rolePeople;
    private String club;
    private boolean agent;

    @JsonCreator
    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.roleFootball = user.getRoleFootball();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.telephoneNumber = user.getTelephoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.socialNetwork = user.getSocialNetwork();
        this.leadingLeg=user.getLeadingLeg();
        this.rolePeople=user.getRolePeople();
        this.club=user.getClub();
        this.agent=user.isAgent();
    }
}