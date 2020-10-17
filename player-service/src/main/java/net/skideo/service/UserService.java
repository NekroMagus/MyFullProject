package net.skideo.service;

import net.skideo.dto.UserDto;
import net.skideo.model.User;
import net.skideo.model.role.RoleFootball;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void addUser(User user);

    User findById(long id);

    User findByLogin(String login);

    UserDto editUser(UserDto userDto, String login);

    List<User> findByDateOfBirthBetween(LocalDate birth, LocalDate now);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByDateOfBirthBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                 RoleFootball roleFootball, String country);

    List<User> findByDateOfBirthBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByDateOfBirthBetweenAndCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    List<User> findAll();

    void addVideo(String link);
}
