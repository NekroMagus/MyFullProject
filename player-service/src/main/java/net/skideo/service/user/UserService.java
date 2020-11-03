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

    List<User> findByBirthDate(LocalDate birth);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByBirthDateAndRoleFootballAndCountry(LocalDate birth,
                                                                 RoleFootball roleFootball, String country);

    List<User> findByBirthDateAndRoleFootball(LocalDate birth,RoleFootball roleFootball);

    List<User> findByBirthDateAndCountry(LocalDate birth,String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    List<User> findAll();

    UserProfileDto getProfile();

}
