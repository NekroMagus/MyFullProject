package net.skideo.dto;

import net.skideo.model.User;
import com.fasterxml.jackson.annotation.JsonCreator;
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
    public SearchUserDto(User user) {
        this.login = user.getLogin();
        this.roleFootball = user.getRoleFootball();
        this.age = getAge(user.getBirthDate());
        this.country = user.getCountry();
    }

    private int getAge(LocalDate birth) {
        if (birth != null) {
            return Period.between(birth, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
