package net.skideo.service.user;

import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.User;
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
    public void create(User user) {
        user.getInfo().setPassword(passwordEncoder.encode(user.getInfo().getPassword()));
        user.getInfo().setServiceRole(ServiceRole.USER);
        repository.save(user);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public User findByLogin(String login) {
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
    public User editUser(UserDto dto) {
        User user = getCurrentUser();
        if (user.getInfo().getRolePeople() == RolePeople.AMATEUR && dto.isAgent()) {
            throw new IllegalArgumentException("Amateur player can not have agent");
        }
        if (StringUtils.isNotBlank(dto.getEmail())) {
            user.getInfo().setEmail(dto.getEmail());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            user.getInfo().setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            user.getInfo().setSurname(dto.getSurname());
        }
        if (dto.getRoleFootball() != null) {
            user.getInfo().setRoleFootball(dto.getRoleFootball());
        }
        if (StringUtils.isNotBlank(dto.getPhone())) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getBirthDate() != null) {
            user.setBirthDate(dto.getBirthDate());
        }
        if (StringUtils.isNotBlank(dto.getCountry())) {
            user.getInfo().setCountry(dto.getCountry());
        }
        if (StringUtils.isNotBlank(dto.getCity())) {
            user.getInfo().setCity(dto.getCity());
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
        return repository.findByInfoRoleFootball(roleFootball);
    }

    @Override
    public List<User> findByCountry(String country) {
        return findByCountry(country);
    }

    @Override
    public List<User> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                      RoleFootball roleFootball, String country) {
        return repository.findByBirthDateBetweenAndInfoRoleFootballAndInfoCountry(birth, now, roleFootball, country);
    }

    @Override
    public List<User> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return repository.findByBirthDateBetweenAndInfoRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<User> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return repository.findByBirthDateBetweenAndInfoCountry(birth, now, country);
    }

    @Override
    public List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country) {
        return repository.findByInfoRoleFootballAndInfoCountry(roleFootball, country);
    }

    @Override
    public List<User> findAll() {
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
    public User getCurrentUser() {
        return findByLogin(getLoginCurrentUser());
    }


}