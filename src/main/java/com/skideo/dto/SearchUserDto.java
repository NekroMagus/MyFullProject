package com.skideo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.skideo.controller.SearchUserController;
import com.skideo.model.User;
import com.skideo.model.role.RoleFootball;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.time.Period;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
public class SearchUserDto extends ResourceSupport {

    private String login;
    private RoleFootball roleFootball;
    private long age;
    private String country;

    @JsonCreator
    public SearchUserDto(User user) {
        this.login = user.getLogin();
        this.roleFootball = user.getRoleFootball();
        this.age = getAge(user.getDateOfBirth());
        this.country = user.getCountry();
        add(linkTo(methodOn(SearchUserController.class).getUserById(user.getId())).withRel("User"));
    }

    private long getAge(LocalDate birth) {
        if (birth != null) {
            return Period.between(birth, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
