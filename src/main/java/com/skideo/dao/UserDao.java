package com.skideo.dao;

import com.skideo.model.User;
import com.skideo.model.role.RoleFootball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findById(long id);
    User findByLogin(String login);
    List<User> findByDateOfBirthBetween(LocalDate birth, LocalDate now);
    List<User> findByRoleFootball(RoleFootball roleFootball);
    List<User> findByCountry(String country);
}