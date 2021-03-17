package net.skideo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.skideo.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    private long id;
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
    private LeadingLeg leadingLeg;
    private boolean agent;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getInfo().getName();
        this.surname = user.getInfo().getSurname();
        this.roleFootball = user.getInfo().getRoleFootball();
        this.email = user.getInfo().getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate();
        this.country = user.getInfo().getCountry();
        this.city = user.getInfo().getCity();
        this.linkSocialNetwork = user.getLinkSocialNetwork();
        this.leadingLeg = user.getLeadingLeg();
        this.agent = user.isHasAgent();
    }
}