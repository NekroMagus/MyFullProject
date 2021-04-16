package net.skideo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.skideo.model.City;
import net.skideo.model.Player;
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
    private City city;
    private String linkSocialNetwork;
    private LeadingLeg leadingLeg;
    private boolean agent;

    public UserDto(Player player) {
        this.id = player.getId();
        this.name = player.getUser().getName();
        this.surname = player.getUser().getSurname();
        this.roleFootball = player.getRoleFootball();
        this.email = player.getUser().getEmail();
        this.phone = player.getPhone();
        this.birthDate = player.getBirthDate();
        this.city = player.getUser().getCity();
        this.linkSocialNetwork = player.getLinkSocialNetwork();
        this.leadingLeg = player.getLeadingLeg();
        this.agent = player.isHasAgent();
    }
}