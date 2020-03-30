package com.github.skideo.service;

import com.github.skideo.dto.UserDto;
import com.github.skideo.model.User;
import com.github.skideo.model.role.RoleFootball;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void addUser(User user);

    User findById(long id);

    User findByLogin(String login);

    void editUser(UserDto userDto);

    List<User> findByDateOfBirthBetween(LocalDate birth, LocalDate now);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByDateOfBirthBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                 RoleFootball roleFootball, String country);

    List<User> findByDateOfBirthBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByDateOfBirthBetweenAndCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    List<User> findAll();
}
