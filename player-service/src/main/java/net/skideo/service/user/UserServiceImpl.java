package net.skideo.service.user;

import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Player;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(Player player) {
        player.getInfo().setPassword(passwordEncoder.encode(player.getInfo().getPassword()));
        player.getInfo().setServiceRole(ServiceRole.USER);
        repository.save(player);
    }

    @Override
    public Player findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public Player findByLogin(String login) {
        return repository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public long getId(String login) {
        return repository.findIdByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("User not found")
        ).getId();
    }

    @Override
    public Player editUser(UserDto dto) {
        Player player = getCurrentUser();
        if (player.getInfo().getRolePeople() == RolePeople.AMATEUR && dto.isAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }
        if (StringUtils.isNotBlank(dto.getEmail())) {
            player.getInfo().setEmail(dto.getEmail());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            player.getInfo().setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            player.getInfo().setSurname(dto.getSurname());
        }
        if (dto.getRoleFootball() != null) {
            player.getInfo().setRoleFootball(dto.getRoleFootball());
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            player.setPhone(dto.getPhone());
        }
        if (dto.getBirthDate() != null) {
            player.setBirthDate(dto.getBirthDate());
        }
        if (StringUtils.isNotBlank(dto.getCountry())) {
            player.getInfo().setCountry(dto.getCountry());
        }
        if (StringUtils.isNotBlank(dto.getCity())) {
            player.getInfo().setCity(dto.getCity());
        }
        if (StringUtils.isNotBlank(dto.getLinkSocialNetwork())) {
            player.setLinkSocialNetwork(dto.getLinkSocialNetwork());
        }
        if (dto.getLeadingLeg() != null) {
            player.setLeadingLeg(dto.getLeadingLeg());
        }
        player.setHasAgent(dto.isAgent());
        return repository.save(player);
    }

    @Override
    public List<Player> findByBirthDateBetween(LocalDate birth, LocalDate now) {
        return repository.findByBirthDateBetween(birth, now);
    }

    @Override
    public List<Player> findByRoleFootball(RoleFootball roleFootball) {
        return repository.findByInfoRoleFootball(roleFootball);
    }

    @Override
    public List<Player> findByCountry(String country) {
        return findByCountry(country);
    }

    @Override
    public List<Player> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                        RoleFootball roleFootball, String country) {
        return repository.findByBirthDateBetweenAndInfoRoleFootballAndInfoCountry(birth, now, roleFootball, country);
    }

    @Override
    public List<Player> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return repository.findByBirthDateBetweenAndInfoRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<Player> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return repository.findByBirthDateBetweenAndInfoCountry(birth, now, country);
    }

    @Override
    public List<Player> findByRoleFootballAndCountry(RoleFootball roleFootball, String country) {
        return repository.findByInfoRoleFootballAndInfoCountry(roleFootball, country);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public UserProfileDto getProfile(long id) {
        return repository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public String getLoginCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Player getCurrentUser() {
        return findByLogin(getLoginCurrentUser());
    }


}