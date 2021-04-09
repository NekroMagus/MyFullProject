package net.skideo.repository;

import net.skideo.dto.*;
import net.skideo.model.Player;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface PlayerRepository extends BaseRepository<Player> {

    Optional<UserProfileDto> findProfileById(long id);

    Page<UserNSDto> findAllByUserNameStartsWithOrUserSurnameStartsWith(String name, String surname, Pageable pageable);

    List<Player> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<Player> findByUserRoleFootball(RoleFootball roleFootball);

    List<Player> findByBirthDateBetweenAndUserRoleFootballAndUserCityCountryName(LocalDate birth, LocalDate now,
                                                                         RoleFootball roleFootball, String name);

    List<Player> findByBirthDateBetweenAndUserRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<Player> findByBirthDateBetweenAndUserCityCountryName(LocalDate birth, LocalDate now, String name);

    List<Player> findByUserRoleFootballAndUserCityCountryName(RoleFootball roleFootball, String name);

    Page<Player> findAll(Pageable pageable);

    Page<UserShortInfoDto> findUsersByUserRolePeople(RolePeople rolePeople, Pageable pageable);

    Page<SearchDto> findAllByUserCityCountryNameOrUserRoleFootballOrHasAgentOrUserRolePeopleOrLeadingLegOrBirthDate(String name, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate birthDate, Pageable pageable);

    List<Player> findByUserCityCountryName(String name);

    List<Player> findAll();

}