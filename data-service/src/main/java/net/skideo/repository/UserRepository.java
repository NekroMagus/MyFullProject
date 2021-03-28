package net.skideo.repository;

import net.skideo.dto.*;
import net.skideo.model.User;
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
public interface UserRepository extends BaseRepository<User> {

    Optional<UserProfileDto> findProfileById(long id);

    Page<UserNSDto> findAllByInfoNameStartsWithOrInfoSurnameStartsWith(String name, String surname, Pageable pageable);

    List<User> findByBirthDateBetween(LocalDate birth, LocalDate now);

    List<User> findByInfoRoleFootball(RoleFootball roleFootball);

    List<User> findByBirthDateBetweenAndInfoRoleFootballAndInfoCountry(LocalDate birth, LocalDate now,
                                                                       RoleFootball roleFootball, String country);

    List<User> findByBirthDateBetweenAndInfoRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball);

    List<User> findByBirthDateBetweenAndInfoCountry(LocalDate birth, LocalDate now, String country);

    List<User> findByInfoRoleFootballAndInfoCountry(RoleFootball roleFootball, String country);

    Page<User> findAll(Pageable pageable);

    Page<UserShortInfoDto> findUsersByInfoRolePeople(RolePeople rolePeople, Pageable pageable);

    Page<SearchDto> findAllByInfoCountryOrInfoRoleFootballOrHasAgentOrInfoRolePeopleOrLeadingLegOrBirthDate(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate birthDate, Pageable pageable);

    List<User> findAll();

}