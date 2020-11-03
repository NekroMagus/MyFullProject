package net.skideo.dao;

import net.skideo.model.User;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    User findById(long id);

    User findByLogin(String login);

    List<User> findByBirthDate(LocalDate birth);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByBirthDateAndRoleFootballAndCountry(LocalDate birth,
                                                                 RoleFootball roleFootball, String country);

    List<User> findByBirthDateAndRoleFootball(LocalDate birth, RoleFootball roleFootball);

    List<User> findByBirthDateAndCountry(LocalDate birth,String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByCountryAndRoleFootballAndAgentAndRolePeopleAndLeadingLegAndBirthDate(String country,RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate birthDate,Pageable pageable);

    boolean existsByLogin(String login);

}