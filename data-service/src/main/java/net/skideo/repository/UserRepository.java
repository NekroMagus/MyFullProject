package net.skideo.repository;

import net.skideo.dto.projections.UserAuthProjection;
import net.skideo.dto.projections.UserProfileProjection;
import net.skideo.dto.projections.UserProjection;
import net.skideo.model.User;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginIgnoreCase(String login);

    Optional<UserProjection> findByLogin(String login);

    Optional<UserAuthProjection> findAuthByLoginIgnoreCase(String login);

    UserProfileProjection findProjectionByLoginIgnoreCase(String login);

    List<User> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<User> findByRoleFootball(RoleFootball roleFootball);

    List<User> findByCountry(String country);

    List<User> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                               RoleFootball roleFootball, String country);

    List<User> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByCountryAndRoleFootballAndHasAgentAndRolePeopleAndLeadingLegAndBirthDate(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate birthDate, Pageable pageable);

    boolean existsByLogin(String login);

}