package net.skideo.service.player;

import net.skideo.dto.AuthDto;
import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Player;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.city.CityService;
import net.skideo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;
    private final CityService cityService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(Player player) {
        player.getUser().setPassword(passwordEncoder.encode(player.getUser().getPassword()));
        repository.save(player);
    }

    @Override
    public Player findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Player not found")
        );
    }

    @Override
    public Player findByLogin(String login) {
        return repository.findByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Player not found")
        );
    }

    @Override
    public long getIdByLogin(String login) {
        return repository.findIdByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Player not found")
        ).getId();
    }

    @Override
    @Transactional
    public void updateLoginAndPassword(AuthDto authDto) {
        Player currentPlayer = getCurrentUser();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            if(userService.isExistsByLogin(authDto.getLogin())) {
                throw new AlreadyExistsException("User already exists");
            }
            currentPlayer.getUser().setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            currentPlayer.getUser().setPassword(passwordEncoder.encode(authDto.getPassword()));
        }

        repository.save(currentPlayer);
    }

    @Override
    @Transactional
    public Player editUser(UserDto dto) {
        Player player = getCurrentUser();
        if (player.getRolePeople() == RolePeople.AMATEUR && dto.isAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }
        if (StringUtils.isNotBlank(dto.getEmail())) {
            player.getUser().setEmail(dto.getEmail());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            player.getUser().setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            player.getUser().setSurname(dto.getSurname());
        }
        if (dto.getRoleFootball() != null) {
            player.setRoleFootball(dto.getRoleFootball());
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            player.setPhone(dto.getPhone());
        }
        if (dto.getBirthDate() != null) {
            player.setBirthDate(dto.getBirthDate());
        }
        if (StringUtils.isNotBlank(dto.getCity().getName()) && StringUtils.isNotBlank(dto.getCity().getCountry().getName())) {
            player.getUser().setCity(cityService.getCity(dto.getCity().getName(),dto.getCity().getCountry().getName()));
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
        return repository.findByRoleFootball(roleFootball);
    }

    @Override
    public List<Player> findByCountry(String country) {
        return repository.findByUserCityCountryName(country);
    }

    @Override
    public List<Player> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                        RoleFootball roleFootball, String country) {
        return repository.findByBirthDateBetweenAndRoleFootballAndUserCityCountryName(birth, now, roleFootball, country);
    }

    @Override
    public List<Player> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return repository.findByBirthDateBetweenAndRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<Player> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return repository.findByBirthDateBetweenAndUserCityCountryName(birth, now, country);
    }

    @Override
    public List<Player> findByRoleFootballAndCountry(RoleFootball roleFootball, String country) {
        return repository.findByRoleFootballAndUserCityCountryName(roleFootball, country);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public UserProfileDto getProfile(long id) {
        return repository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("Player not found")
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