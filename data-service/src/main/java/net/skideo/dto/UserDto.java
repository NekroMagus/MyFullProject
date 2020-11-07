package net.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.skideo.model.Club;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RolePeople;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String surname;
    private RoleFootball roleFootball;

    @Email
    private String email;
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String country;
    private String city;
    private String linkSocialNetwork;
    private String video;
    private LeadingLeg leadingLeg;
    private boolean agent;
    private Club club;

    @JsonCreator
    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.roleFootball = user.getRoleFootball();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.linkSocialNetwork = user.getLinkSocialNetwork();
        this.leadingLeg = user.getLeadingLeg();
        this.agent = user.isHasAgent();
        this.club = user.getClub();
    }
}