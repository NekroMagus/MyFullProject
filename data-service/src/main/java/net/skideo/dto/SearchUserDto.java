package net.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.skideo.model.Player;
import lombok.Data;
import net.skideo.model.enums.RoleFootball;

import java.time.LocalDate;
import java.time.Period;

@Data
public class SearchUserDto {

    private String login;
    private RoleFootball roleFootball;
    private int age;
    private String country;

    @JsonCreator
    public SearchUserDto(Player player) {
        this.login = player.getUser().getLogin();
        this.roleFootball = player.getUser().getRoleFootball();
        this.age = getAge(player.getBirthDate());
        this.country = player.getUser().getCity().getCountry().getName();
    }

    private int getAge(LocalDate birth) {
        if (birth != null) {
            return Period.between(birth, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
