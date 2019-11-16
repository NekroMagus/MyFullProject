package com.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.skideo.controller.ProfileController;
import com.skideo.model.User;
import com.skideo.model.role.RoleFootball;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
@NoArgsConstructor
public class UserDto extends ResourceSupport {
    private RoleFootball roleFootball;
    private String login;
    private String email;
    private String telephoneNumber;
    private LocalDate dateOfBirth;
    private String country;
    private String socialNetwork;

    @JsonCreator
    public UserDto(User user) {
        this.roleFootball = user.getRoleFootball();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.telephoneNumber = user.getTelephoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
        this.country = user.getCountry();
        this.socialNetwork = user.getSocialNetwork();
        add(linkTo(methodOn(ProfileController.class).getUserById(user.getId())).withRel("User"));
    }
}