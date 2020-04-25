package com.github.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.skideo.model.User;
import com.github.skideo.model.role.RoleFootball;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    }
}