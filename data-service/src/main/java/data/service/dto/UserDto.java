package data.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import data.service.model.role.LeadingLeg;
import data.service.model.role.RolePeople;
import lombok.Data;
import lombok.NoArgsConstructor;
import data.service.model.User;
import data.service.model.role.RoleFootball;

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
        this.video = user.getVideo();
        this.leadingLeg=user.getLeadingLeg();
        this.rolePeople=user.getRolePeople();
        this.club=user.getClub();
        this.agent=user.isAgent();
    }
}