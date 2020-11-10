package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skideo.dto.UserDto;
import net.skideo.dto.UserRegistrationDto;
import net.skideo.dto.projections.UserAuthProjection;
import net.skideo.dto.projections.UserProfileProjection;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.UserNotFoundException;
import net.skideo.model.User;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.dto.projections.UserProjection;
import net.skideo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(UserRegistrationDto dto) {
        final String password = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getLogin(), password, dto.getRolePeople(), dto.isHasAgent());

        repository.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLoginIgnoreCase(login);
    }

    @Override
    public UserProjection findUserProjectionByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<UserAuthProjection> findAuthByLogin(String login) {
        return repository.findAuthByLoginIgnoreCase(login);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id).orElseThrow(
                UserNotFoundException::new
        );
    }

    @Override
    public User editUser(UserDto dto) {
        User user = getCurrentUser();
        if (user.getRolePeople() == RolePeople.AMATEUR && dto.isAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }
        if (StringUtils.isNotBlank(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            user.setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            user.setSurname(dto.getSurname());
        }
        if (dto.getRoleFootball() != null) {
            user.setRoleFootball(dto.getRoleFootball());
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getBirthDate() != null) {
            user.setBirthDate(dto.getBirthDate());
        }
        if (StringUtils.isNotBlank(dto.getCountry())) {
            user.setCountry(dto.getCountry());
        }
        if (StringUtils.isNotBlank(dto.getCity())) {
            user.setCity(dto.getCity());
        }
        if (StringUtils.isNotBlank(dto.getLinkSocialNetwork())) {
            user.setLinkSocialNetwork(dto.getLinkSocialNetwork());
        }
        if (dto.getLeadingLeg() != null) {
            user.setLeadingLeg(dto.getLeadingLeg());
        }
        user.setHasAgent(dto.isAgent());
        return repository.save(user);
    }

    @Override
    public List<User> findByBirthDateBetween(LocalDate birth, LocalDate now) {
        return repository.findByBirthDateBetween(birth, now);
    }

    @Override
    public List<User> findByRoleFootball(RoleFootball roleFootball) {
        return repository.findByRoleFootball(roleFootball);
    }

    @Override
    public List<User> findByCountry(String country) {
        return findByCountry(country);
    }

    @Override
    public List<User> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                      RoleFootball roleFootball, String country) {
        return repository.findByBirthDateBetweenAndRoleFootballAndCountry(birth, now, roleFootball, country);
    }

    @Override
    public List<User> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return repository.findByBirthDateBetweenAndRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<User> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return repository.findByBirthDateBetweenAndCountry(birth, now, country);
    }

    @Override
    public List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country) {
        return repository.findByRoleFootballAndCountry(roleFootball, country);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User getCurrentUser() {
        return findByLogin(getCurrentLogin()).orElseThrow(() -> {
            log.error("Cannot get current user, Security error in class: {}", this.getClass().getSimpleName());
            throw new NotFoundException("User not found");
        });
    }

    @Override
    public UserProfileProjection getProfile() {
        return repository.findProjectionByLoginIgnoreCase(getCurrentLogin());
    }

    private String getCurrentLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}