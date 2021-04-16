package net.skideo.service.player;

import net.skideo.dto.AuthDto;
import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.model.Player;
import net.skideo.model.enums.RoleFootball;

import java.time.LocalDate;
import java.util.List;

public interface PlayerService {

    void create(Player player);

    Player findById(long id);

    Player findByLogin(String login);

    void updateLoginAndPassword(AuthDto authDto);

    Player editUser(UserDto userDto);

    List<Player> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<Player> findByRoleFootball(RoleFootball roleFootball);

    List<Player> findByCountry(String country);

    List<Player> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                 RoleFootball roleFootball, String country);

    List<Player> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<Player> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country);

    List<Player> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    List<Player> findAll();

    long getIdByLogin(String login);

    UserProfileDto getProfile(long id);

    Player getCurrentUser();

    String getLoginCurrentUser();

}
