package net.skideo.service.user;

import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void addUser(User user);

    User findById(long id);

    User findByLogin(String login);

    UserDto editUser(UserDto userDto, String login);

    List<User> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                 RoleFootball roleFootball, String country);

    List<User> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    List<User> findAll();

    UserProfileDto getProfile();

}
