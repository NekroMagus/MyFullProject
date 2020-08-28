package net.skideo.dao;

import net.skideo.model.User;
import net.skideo.model.role.RoleFootball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findById(long id);

    User findByLogin(String login);

    List<User> findByDateOfBirthBetween(LocalDate birth, LocalDate now);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByDateOfBirthBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                 RoleFootball roleFootball, String country);

    List<User> findByDateOfBirthBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByDateOfBirthBetweenAndCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);
}