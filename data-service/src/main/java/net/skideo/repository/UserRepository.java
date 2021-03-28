package net.skideo.repository;

import net.skideo.dto.*;
import net.skideo.dto.projections.*;
import net.skideo.model.Academy;
import net.skideo.model.User;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByInfoLoginIgnoreCase(String login);

    Optional<User> findByInfoLogin(String login);

    Page<UserNSDto> findAllByInfoNameStartsWithOrInfoSurnameStartsWith(String name, String surname, Pageable pageable);

    Optional<IdProjection> findIdByInfoLogin(String login);

    UserProfileDto findProfileById(long id);

    List<User> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<User> findByInfoRoleFootball(RoleFootball roleFootball);

    List<User> findByInfoCountry(String country);

    List<User> findByBirthDateBetweenAndInfoRoleFootballAndInfoCountry(LocalDate birth, LocalDate now,
                                                                       RoleFootball roleFootball, String country);

    List<User> findByBirthDateBetweenAndInfoRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByBirthDateBetweenAndInfoCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByInfoRoleFootballAndInfoCountry(RoleFootball roleFootball, String country);

    Page<User> findAll(Pageable pageable);

    Page<UserShortInfoDto> findUsersByInfoRolePeople(RolePeople rolePeople, Pageable pageable);

    Page<SearchDto> findAllByInfoCountryOrInfoRoleFootballOrHasAgentOrInfoRolePeopleOrLeadingLegOrBirthDate(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate birthDate, Pageable pageable);

    Page<UserShortInfoDto> findPlayersByInfoLogin(String login, Pageable pageable);

    boolean existsByInfoLogin(String login);

    List<User> findAll();

}