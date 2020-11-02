package net.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.skideo.model.Club;
import net.skideo.model.Like;
import net.skideo.model.Video;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RolePeople;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;

import javax.validation.constraints.NotNull;
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
    private LocalDate birthDate;
    private String country;
    private String city;
    private String linkSocialNetwork;
    private String video;
    private LeadingLeg leadingLeg;
    private RolePeople rolePeople;
    private boolean agent;
    private Club club;

    @JsonCreator
    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.roleFootball = user.getRoleFootball();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.telephoneNumber = user.getTelephoneNumber();
        this.birthDate = user.getBirthDate();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.linkSocialNetwork = user.getLinkSocialNetwork();
        this.leadingLeg=user.getLeadingLeg();
        this.rolePeople=user.getRolePeople();
        this.agent=user.isAgent();
        this.club=user.getClub();
    }
}