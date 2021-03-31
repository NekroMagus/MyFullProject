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
public interface UserRepository extends BaseRepository<Player> {

    Optional<UserProfileDto> findProfileById(long id);

    Page<UserNSDto> findAllByInfoNameStartsWithOrInfoSurnameStartsWith(String name, String surname, Pageable pageable);

    List<Player> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<Player> findByInfoRoleFootball(RoleFootball roleFootball);

    List<Player> findByBirthDateBetweenAndInfoRoleFootballAndInfoCountry(LocalDate birth, LocalDate now,
                                                                         RoleFootball roleFootball, String country);

    List<Player> findByBirthDateBetweenAndInfoRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<Player> findByBirthDateBetweenAndInfoCountry(LocalDate birth, LocalDate now, String country);

    List<Player> findByInfoRoleFootballAndInfoCountry(RoleFootball roleFootball, String country);

    Page<Player> findAll(Pageable pageable);

    Page<UserShortInfoDto> findUsersByInfoRolePeople(RolePeople rolePeople, Pageable pageable);

    Page<SearchDto> findAllByInfoCountryOrInfoRoleFootballOrHasAgentOrInfoRolePeopleOrLeadingLegOrBirthDate(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate birthDate, Pageable pageable);

    List<Player> findAll();

}